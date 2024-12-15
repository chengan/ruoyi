<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="任务ID" prop="taskId">
        <el-input
          v-model="queryParams.taskId"
          placeholder="请输入任务ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检查时间" prop="time">
        <el-date-picker clearable
          v-model="queryParams.time"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择检查时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="负责人" prop="chairman">
        <el-input
          v-model="queryParams.chairman"
          placeholder="请输入负责人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['RecordQuestion:RecordQuestion:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['RecordQuestion:RecordQuestion:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['RecordQuestion:RecordQuestion:remove']"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['RecordQuestion:RecordQuestion:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="RecordQuestionList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="任务ID" align="center" prop="taskId" />
      <el-table-column label="检查时间" align="center" prop="time" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.time, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="问题描述" align="center" prop="questionDescription" />
      <el-table-column label="照片存放" align="center" prop="photos" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.photos" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="整改措施" align="center" prop="rectificationMeasures" />
      <el-table-column label="检查时间" align="center" prop="checkTime" />
      <el-table-column label="负责人" align="center" prop="chairman" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['RecordQuestion:RecordQuestion:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['RecordQuestion:RecordQuestion:remove']"-->
<!--          >删除</el-button>-->
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改整改记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="检查时间" prop="time">
          <el-date-picker clearable
            v-model="form.time"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择检查时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="问题描述" prop="questionDescription">
          <el-input v-model="form.questionDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="照片存放" prop="photos">
          <image-upload v-model="form.photos"/>
        </el-form-item>
        <el-form-item label="整改措施" prop="rectificationMeasures">
          <el-input v-model="form.rectificationMeasures" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="负责人" prop="chairman">
          <el-input v-model="form.chairman" placeholder="请输入负责人" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRecordQuestion, getRecordQuestion, delRecordQuestion, addRecordQuestion, updateRecordQuestion } from "@/api/RecordQuestion/RecordQuestion";

export default {
  name: "RecordQuestion",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 整改记录表格数据
      RecordQuestionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        taskId: null,
        time: null,
        chairman: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询整改记录列表 */
    getList() {
      this.loading = true;
      listRecordQuestion(this.queryParams).then(response => {
        this.RecordQuestionList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        taskId: null,
        time: null,
        questionDescription: null,
        photos: null,
        rectificationMeasures: null,
        checkTime: null,
        chairman: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.taskId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加整改记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const taskId = row.taskId || this.ids
      getRecordQuestion(taskId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改整改记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.taskId != null) {
            updateRecordQuestion(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRecordQuestion(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const taskIds = row.taskId || this.ids;
      this.$modal.confirm('是否确认删除整改记录编号为"' + taskIds + '"的数据项？').then(function() {
        return delRecordQuestion(taskIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('RecordQuestion/RecordQuestion/export', {
        ...this.queryParams
      }, `RecordQuestion_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
