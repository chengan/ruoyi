<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="验收编号" prop="reportId">
        <el-input
          v-model="queryParams.reportId"
          placeholder="请输入验收编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目编号" prop="projectId">
        <el-input
          v-model="queryParams.projectId"
          placeholder="请输入项目编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="验收项目" prop="acceptanceItem">
        <el-input
          v-model="queryParams.acceptanceItem"
          placeholder="请输入验收项目"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="验收结果" prop="acceptanceResult">
        <el-input
          v-model="queryParams.acceptanceResult"
          placeholder="请输入验收结果"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="验收时间" prop="acceptanceTime">
        <el-date-picker clearable
          v-model="queryParams.acceptanceTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择验收时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="验收人员" prop="picNumber">
        <el-input
          v-model="queryParams.picNumber"
          placeholder="请输入验收人员编号"
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
          v-hasPermi="['acceptance:acceptance:add']"
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
          v-hasPermi="['acceptance:acceptance:edit']"
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
          v-hasPermi="['acceptance:acceptance:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['acceptance:acceptance:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="acceptanceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="验收编号" align="center" prop="reportId" />
      <el-table-column label="项目编号" align="center" prop="projectId" />
      <el-table-column label="验收项目" align="center" prop="acceptanceItem" />
      <el-table-column label="验收结果" align="center" prop="acceptanceResult" />
      <el-table-column label="验收时间" align="center" prop="acceptanceTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.acceptanceTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="验收人员编号" align="center" prop="picNumber" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['acceptance:acceptance:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['acceptance:acceptance:remove']"
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

    <!-- 添加或修改竣工验收管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目编号" prop="projectId">
          <el-input v-model="form.projectId" placeholder="请输入项目编号" />
        </el-form-item>
        <el-form-item label="验收项目" prop="acceptanceItem">
          <el-input v-model="form.acceptanceItem" placeholder="请输入验收项目" />
        </el-form-item>
        <el-form-item label="验收结果" prop="acceptanceResult">
          <el-input v-model="form.acceptanceResult" placeholder="请输入验收结果" />
        </el-form-item>
        <el-form-item label="验收时间" prop="acceptanceTime">
          <el-date-picker clearable
            v-model="form.acceptanceTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择验收时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="验收人员编号" prop="picNumber">
          <el-input v-model="form.picNumber" placeholder="请输入验收人员编号" />
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
import { listAcceptance, getAcceptance, delAcceptance, addAcceptance, updateAcceptance } from "@/api/acceptance/acceptance";

export default {
  name: "Acceptance",
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
      // 竣工验收管理表格数据
      acceptanceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        reportId: null,
        projectId: null,
        acceptanceItem: null,
        acceptanceResult: null,
        acceptanceTime: null,
        picNumber: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        projectId: [
          { required: true, message: "项目编号不能为空", trigger: "blur" }
        ],
        acceptanceItem: [
          { required: true, message: "验收项目不能为空", trigger: "blur" }
        ],
        acceptanceResult: [
          { required: true, message: "验收结果不能为空", trigger: "blur" }
        ],
        acceptanceTime: [
          { required: true, message: "验收时间不能为空", trigger: "blur" }
        ],
        picNumber: [
          { required: true, message: "验收人员编号不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询竣工验收管理列表 */
    getList() {
      this.loading = true;
      listAcceptance(this.queryParams).then(response => {
        this.acceptanceList = response.rows;
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
        reportId: null,
        projectId: null,
        acceptanceItem: null,
        acceptanceResult: null,
        acceptanceTime: null,
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
      this.ids = selection.map(item => item.reportId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加竣工验收管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const reportId = row.reportId || this.ids
      getAcceptance(reportId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改竣工验收管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.reportId != null) {
            updateAcceptance(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addAcceptance(this.form).then(response => {
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
      const reportIds = row.reportId || this.ids;
      this.$modal.confirm('是否确认删除竣工验收管理编号为"' + reportIds + '"的数据项？').then(function() {
        return delAcceptance(reportIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('acceptance/acceptance/export', {
        ...this.queryParams
      }, `acceptance_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
