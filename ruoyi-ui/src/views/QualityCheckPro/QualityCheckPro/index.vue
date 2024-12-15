<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="任务wbs" prop="itemWbs">
        <el-input
          v-model="queryParams.itemWbs"
          placeholder="请输入任务wbs"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目名称" prop="taskName">
        <el-input
          v-model="queryParams.taskName"
          placeholder="请输入项目名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检查项目" prop="itemName">
        <el-input
          v-model="queryParams.itemName"
          placeholder="请输入检查项目"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="施工单位" prop="companyName">
        <el-input
          v-model="queryParams.companyName"
          placeholder="请输入施工单位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>




<!--      <el-form-item label="检查结果" prop="checkResult">-->
<!--        <el-input-->
<!--          v-model="queryParams.checkResult"-->
<!--          placeholder="请输入检查结果"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->

<el-form-item label="检查结果" prop="checkResult">
      <el-select label="检查结果" v-model="queryParams.checkResult" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value">
        </el-option>
      </el-select>
</el-form-item>




      <el-form-item label="检查时间" prop="checkTime">
        <el-date-picker clearable
          v-model="queryParams.checkTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="选择检查时间">
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
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['QualityCheckPro:QualityCheckPro:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="info"
          plain
          icon="el-icon-sort"
          size="mini"
          @click="toggleExpandAll"
        >展开/折叠</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table
      v-if="refreshTable"
      v-loading="loading"
      :data="QualityCheckProList"
      row-key="taskId"
      :default-expand-all="isExpandAll"
      :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
    >
      <el-table-column label="任务wbs" prop="itemWbs" />
      <el-table-column label="项目名称" align="center" prop="taskName" />
      <el-table-column label="检查项目" align="center" prop="itemName" />
      <el-table-column label="施工单位" align="center" prop="companyName" />
<!--      <el-table-column label="上级任务" align="center" prop="parentId" />-->
      <el-table-column label="检查结果" align="center" prop="checkResult" />
      <el-table-column label="照片存放" align="center" prop="photos" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.photos" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="问题描述" align="center" prop="questionDescription" />
      <el-table-column label="整改措施" align="center" prop="rectificationMeasures" />
      <el-table-column label="检查时间" align="center" prop="checkTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="负责人" align="center" prop="chairman" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['QualityCheckPro:QualityCheckPro:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-plus"
            @click="handleAdd(scope.row)"
            v-hasPermi="['QualityCheckPro:QualityCheckPro:add']"
          >新增</el-button>
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['QualityCheckPro:QualityCheckPro:remove']"-->
<!--          >删除</el-button>-->
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加或修改质量检查上报对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="任务wbs" prop="itemWbs">
          <el-input v-model="form.itemWbs" placeholder="请输入任务wbs" />
        </el-form-item>
        <el-form-item label="项目名称" prop="taskName">
          <el-input v-model="form.taskName" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="检查项目" prop="itemName">
          <el-input v-model="form.itemName" placeholder="请输入检查项目" />
        </el-form-item>
        <el-form-item label="施工单位" prop="companyName">
          <el-input v-model="form.companyName" placeholder="请输入施工单位" />
        </el-form-item>
        <el-form-item label="上级任务" prop="parentId">
          <treeselect v-model="form.parentId" :options="QualityCheckProOptions" :normalizer="normalizer" placeholder="请选择上级任务" />
        </el-form-item>
        <el-form-item label="检查结果" prop="checkResult">
          <el-select label="检查结果" v-model="form.checkResult" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="照片存放" prop="photos">
          <image-upload v-model="form.photos"/>
        </el-form-item>
        <el-form-item label="问题描述" prop="questionDescription">
          <el-input v-model="form.questionDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="整改措施" prop="rectificationMeasures">
          <el-input v-model="form.rectificationMeasures" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="检查时间" prop="checkTime">
          <el-date-picker clearable
            v-model="form.checkTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="选择检查时间">
          </el-date-picker>
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
import { listQualityCheckPro, getQualityCheckPro, delQualityCheckPro, addQualityCheckPro, updateQualityCheckPro } from "@/api/QualityCheckPro/QualityCheckPro";
import Treeselect from "@riophae/vue-treeselect";
import "@riophae/vue-treeselect/dist/vue-treeselect.css";

export default {
  name: "QualityCheckPro",
  components: {
    Treeselect
  },
  data() {
    return {



      options: [{
        value: '合格',
        label: '合格'
      }, {
        value: '整改',
        label: '整改'
      }],
      checkResult: '',



      // 遮罩层
      loading: true,
      // 显示搜索条件
      showSearch: true,
      // 质量检查上报表格数据
      QualityCheckProList: [],
      // 质量检查上报树选项
      QualityCheckProOptions: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 是否展开，默认全部展开
      isExpandAll: true,
      // 重新渲染表格状态
      refreshTable: true,
      // 查询参数
      queryParams: {
        itemWbs: null,
        taskName: null,
        itemName: null,
        companyName: null,
        checkResult: null,
        checkTime: null,
        chairman: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        itemWbs: [
          { required: true, message: "任务wbs不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询质量检查上报列表 */
    getList() {
      this.loading = true;
      listQualityCheckPro(this.queryParams).then(response => {
        this.QualityCheckProList = this.handleTree(response.data, "taskId", "parentId");
        this.loading = false;
      });
    },
    /** 转换质量检查上报数据结构 */
    normalizer(node) {
      if (node.children && !node.children.length) {
        delete node.children;
      }
      return {
        id: node.taskId,
        label: node.itemWbs,
        children: node.children
      };
    },
	/** 查询质量检查上报下拉树结构 */
    getTreeselect() {
      listQualityCheckPro().then(response => {
        this.QualityCheckProOptions = [];
        const data = { taskId: 0, itemWbs: '顶级节点', children: [] };
        data.children = this.handleTree(response.data, "taskId", "parentId");
        this.QualityCheckProOptions.push(data);
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
        itemWbs: null,
        taskName: null,
        itemName: null,
        companyName: null,
        parentId: null,
        checkResult: null,
        photos: null,
        questionDescription: null,
        rectificationMeasures: null,
        checkTime: null,
        chairman: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.getTreeselect();
      if (row != null && row.taskId) {
        this.form.parentId = row.taskId;
      } else {
        this.form.parentId = 0;
      }
      this.open = true;
      this.title = "添加质量检查上报";
    },
    /** 展开/折叠操作 */
    toggleExpandAll() {
      this.refreshTable = false;
      this.isExpandAll = !this.isExpandAll;
      this.$nextTick(() => {
        this.refreshTable = true;
      });
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      this.getTreeselect();
      if (row != null) {
        this.form.parentId = row.parentId;
      }
      getQualityCheckPro(row.taskId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改质量检查上报";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.taskId != null) {
            updateQualityCheckPro(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addQualityCheckPro(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除质量检查上报编号为"' + row.taskId + '"的数据项？').then(function() {
        return delQualityCheckPro(row.taskId);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    }
  }
};
</script>
