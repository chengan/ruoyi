<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目编号" prop="projectId">
        <el-input
          v-model="queryParams.projectId"
          placeholder="请输入项目编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检查项目" prop="checkItem">
        <el-input
          v-model="queryParams.checkItem"
          placeholder="请输入检查项目"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="检查标准" prop="checkStandard">-->
<!--        <el-input-->
<!--          v-model="queryParams.checkStandard"-->
<!--          placeholder="请输入检查标准"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="照片" prop="photos">-->
<!--        <el-input-->
<!--          v-model="queryParams.photos"-->
<!--          placeholder="请输入照片"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="问题描述" prop="questionDescription">
        <el-input
          v-model="queryParams.questionDescription"
          placeholder="请输入问题描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检查结果" prop="checkResult">
        <el-input
          v-model="queryParams.checkResult"
          placeholder="请输入检查结果"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="整改措施" prop="rectificationMeasures">-->
<!--        <el-input-->
<!--          v-model="queryParams.rectificationMeasures"-->
<!--          placeholder="请输入整改措施"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="整改效果" prop="rectificationEffect">-->
<!--        <el-input-->
<!--          v-model="queryParams.rectificationEffect"-->
<!--          placeholder="请输入整改效果"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="检查时间" prop="checkTime">
        <el-date-picker clearable
          v-model="queryParams.checkTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择检查时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="检察人员" prop="picNumber">
        <el-input
          v-model="queryParams.picNumber"
          placeholder="请输入检察人员编号"
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
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['qualitycheck:qualitycheck:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['qualitycheck:qualitycheck:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['qualitycheck:qualitycheck:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['qualitycheck:qualitycheck:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="qualitycheckList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="检查编号" align="center" prop="checkId" />
      <el-table-column label="项目编号" align="center" prop="projectId" />
      <el-table-column label="检查项目" align="center" prop="checkItem" />
      <el-table-column label="检查标准" align="center" prop="checkStandard" />
      <el-table-column label="照片" align="center" prop="photos" />
      <el-table-column label="问题描述" align="center" prop="questionDescription" />
      <el-table-column label="检查结果" align="center" prop="checkResult" />
      <el-table-column label="整改措施" align="center" prop="rectificationMeasures" />
      <el-table-column label="整改效果" align="center" prop="rectificationEffect" />
      <el-table-column label="检查时间" align="center" prop="checkTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="检察人员编号" align="center" prop="picNumber" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['qualitycheck:qualitycheck:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['qualitycheck:qualitycheck:remove']"
          >删除</el-button>
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

    <!-- 添加或修改质量检查管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目编号" prop="projectId">
          <el-input v-model="form.projectId" placeholder="请输入项目编号" />
        </el-form-item>
        <el-form-item label="检查项目" prop="checkItem">
          <el-input v-model="form.checkItem" placeholder="请输入检查项目" />
        </el-form-item>
        <el-form-item label="检查标准" prop="checkStandard">
          <el-input v-model="form.checkStandard" placeholder="请输入检查标准" />
        </el-form-item>
        <el-form-item label="照片" prop="photos">
          <el-input v-model="form.photos" placeholder="请输入照片" />
        </el-form-item>
        <el-form-item label="问题描述" prop="questionDescription">
          <el-input v-model="form.questionDescription" placeholder="请输入问题描述" />
        </el-form-item>
        <el-form-item label="检查结果" prop="checkResult">
          <el-input v-model="form.checkResult" placeholder="请输入检查结果" />
        </el-form-item>
        <el-form-item label="整改措施" prop="rectificationMeasures">
          <el-input v-model="form.rectificationMeasures" placeholder="请输入整改措施" />
        </el-form-item>
        <el-form-item label="整改效果" prop="rectificationEffect">
          <el-input v-model="form.rectificationEffect" placeholder="请输入整改效果" />
        </el-form-item>
        <el-form-item label="检查时间" prop="checkTime">
          <el-date-picker clearable
            v-model="form.checkTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择检查时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="检察人员编号" prop="picNumber">
          <el-input v-model="form.picNumber" placeholder="请输入检察人员编号" />
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
import { listQualitycheck, getQualitycheck, delQualitycheck, addQualitycheck, updateQualitycheck } from "@/api/qualitycheck/qualitycheck";

export default {
  name: "Qualitycheck",
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
      // 质量检查管理表格数据
      qualitycheckList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectId: null,
        checkItem: null,
        checkStandard: null,
        photos: null,
        questionDescription: null,
        checkResult: null,
        rectificationMeasures: null,
        rectificationEffect: null,
        checkTime: null,
        picNumber: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        projectId: [
          { required: true, message: "项目编号不能为空", trigger: "blur" }
        ],
        checkItem: [
          { required: true, message: "检查项目不能为空", trigger: "blur" }
        ],
        checkStandard: [
          { required: true, message: "检查标准不能为空", trigger: "blur" }
        ],
        photos: [
          { required: true, message: "照片不能为空", trigger: "blur" }
        ],
        questionDescription: [
          { required: true, message: "问题描述不能为空", trigger: "blur" }
        ],
        checkResult: [
          { required: true, message: "检查结果不能为空", trigger: "blur" }
        ],
        rectificationMeasures: [
          { required: true, message: "整改措施不能为空", trigger: "blur" }
        ],
        rectificationEffect: [
          { required: true, message: "整改效果不能为空", trigger: "blur" }
        ],
        checkTime: [
          { required: true, message: "检查时间不能为空", trigger: "blur" }
        ],
        picNumber: [
          { required: true, message: "检察人员编号不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询质量检查管理列表 */
    getList() {
      this.loading = true;
      listQualitycheck(this.queryParams).then(response => {
        this.qualitycheckList = response.rows;
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
        checkId: null,
        projectId: null,
        checkItem: null,
        checkStandard: null,
        photos: null,
        questionDescription: null,
        checkResult: null,
        rectificationMeasures: null,
        rectificationEffect: null,
        checkTime: null,
        picNumber: null
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
      this.ids = selection.map(item => item.checkId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加质量检查管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const checkId = row.checkId || this.ids
      getQualitycheck(checkId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改质量检查管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.checkId != null) {
            updateQualitycheck(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addQualitycheck(this.form).then(response => {
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
      const checkIds = row.checkId || this.ids;
      this.$modal.confirm('是否确认删除质量检查管理编号为"' + checkIds + '"的数据项？').then(function() {
        return delQualitycheck(checkIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('qualitycheck/qualitycheck/export', {
        ...this.queryParams
      }, `qualitycheck_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
