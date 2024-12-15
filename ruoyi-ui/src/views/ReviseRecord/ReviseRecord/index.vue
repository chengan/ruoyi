<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目wbs" prop="wbs">
<!--        <el-input-->
<!--          v-model="queryParams.wbs"-->
<!--          placeholder="请输入项目wbs"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->

        <div class="block">
          <span class="demonstration"></span>
          <el-cascader
            v-model="selectedWbs"
            :options="options" :show-all-levels="false"
            :props="{ checkStrictly: true }"
            clearable
            @change="getList"
          ></el-cascader>
        </div>


      </el-form-item>
      <el-form-item label="检查项目" prop="checkItem">
        <el-input
          v-model="queryParams.checkItem"
          placeholder="请输入检查项目"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检查时间" prop="checkTime">
        <el-date-picker clearable
          v-model="queryParams.checkTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择检查时间">
        </el-date-picker>
      </el-form-item>
<!--      <el-form-item label="上传时间">-->
<!--        <el-date-picker-->
<!--          v-model="daterangeUploadTime"-->
<!--          style="width: 240px"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          type="daterange"-->
<!--          range-separator="-"-->
<!--          start-placeholder="开始日期"-->
<!--          end-placeholder="结束日期"-->
<!--        ></el-date-picker>-->
<!--      </el-form-item>-->
      <el-form-item label="负责人" prop="picNumber">
        <el-input
          v-model="queryParams.picNumber"
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
          v-hasPermi="['ReviseRecord:ReviseRecord:add']"
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
          v-hasPermi="['ReviseRecord:ReviseRecord:edit']"
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
          v-hasPermi="['ReviseRecord:ReviseRecord:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['ReviseRecord:ReviseRecord:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ReviseRecordList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="整改ID" align="center" prop="reviseId" />
      <el-table-column label="项目wbs" align="center" prop="wbs" />
      <el-table-column label="检查项目" align="center" prop="checkItem" />
      <el-table-column label="问题描述" align="center" prop="questionDescription" />
      <el-table-column label="整改后照片" align="center" prop="photos" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.photos" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="检查时间" align="center" prop="checkTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="上传时间" align="center" prop="uploadTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.uploadTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="负责人" align="center" prop="picNumber" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['ReviseRecord:ReviseRecord:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['ReviseRecord:ReviseRecord:remove']"
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

    <!-- 添加或修改整改结果上报对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目wbs" prop="wbs">
<!--          <el-input v-model="form.wbs" placeholder="请输入项目wbs" />-->

          <div class="block">
            <span class="demonstration"></span>
            <el-cascader
              v-model="form.wbs"
              :options="options" :show-all-levels="false"
              :props="{ checkStrictly: true }"
              clearable></el-cascader>
          </div>


        </el-form-item>
        <el-form-item label="检查项目" prop="checkItem">
          <el-input v-model="form.checkItem" placeholder="请输入检查项目" />
        </el-form-item>
        <el-form-item label="问题描述" prop="questionDescription">
          <el-input v-model="form.questionDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="整改后照片" prop="photos">
          <image-upload v-model="form.photos"/>
        </el-form-item>
        <el-form-item label="检查时间" prop="checkTime">
          <el-date-picker clearable
            v-model="form.checkTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择检查时间">
          </el-date-picker>
        </el-form-item>
<!--        <el-form-item label="上传时间" prop="uploadTime">-->


<!--          <el-date-picker v-model="form.uploadTime"-->
<!--                          clearable-->
<!--                          placeholder="上传时间"-->
<!--                          type="datetime"-->
<!--                          value-format="yyyy-MM-dd HH:mm:ss"-->
<!--                          :disabled="isadd">-->
<!--          </el-date-picker>-->


<!--        </el-form-item>-->
        <el-form-item label="负责人" prop="picNumber">
          <el-input v-model="form.picNumber" placeholder="请输入负责人" />
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
import { listReviseRecord, getReviseRecord, delReviseRecord, addReviseRecord, updateReviseRecord } from "@/api/ReviseRecord/ReviseRecord";

export default {
  name: "ReviseRecord",
  data() {
    return {

      isadd: false,

      selectedWbs: null,//用于记录返回最后一级的数据

      options: [{
        value: '1',
        label: '1',
        children: [{
          value: '1.1',
          label: '1.1',
          children: [{
            value: '1.1.1',
            label: '1.1.1'
          }, {
            value: '1.1.2',
            label: '1.1.2'
          }, {
            value: '1.1.3',
            label: '1.1.3'
          }, {
            value: '1.1.4',
            label: '1.1.4'
          }]
        }, {
          value: '1.2',
          label: '1.2',
          children: [{
            value: '1.2.1',
            label: '1.2.1'
          }, {
            value: '1.2.2',
            label: '1.2.2'
          },
            {
              value: '1.2.3',
              label: '1.2.3'
            }]
        }]
      }, {
        value: '2',
        label: '2',
        children: [{
          value: '2.1',
          label: '2.1',
          children: [{
            value: '2.1.1',
            label: '2.1.1'
          }, {
            value: '2.1.2',
            label: '2.1.2'
          }, {
            value: '2.1.3',
            label: '2.1.3'
          }, {
            value: '2.1.4',
            label: '2.1.4'
          }, {
            value: '2.1.5',
            label: '2.1.5'
          }]
        }, {
          value: '2.2',
          label: '2.2',
          children: [{
            value: '2.2.1',
            label: '2.2.1'
          }, {
            value: '2.2.2',
            label: '2.2.2'
          }, {
            value: '2.2.3',
            label: '2.2.3'
          }, {
            value: '2.2.4',
            label: '2.2.4'
          }]
        }, {
          value: '2.3',
          label: '2.3',
          children: [{
            value: '2.3.1',
            label: '2.3.1'
          }, {
            value: '2.3.2',
            label: '2.3.2'
          }, {
            value: '2.3.3',
            label: '2.3.3'
          }, {
            value: '2.3.4',
            label: '2.3.4'
          }, {
            value: '2.3.5',
            label: '2.3.5'
          }, {
            value: '2.3.6',
            label: '2.3.6'
          }]
        }, ]
      }],




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
      // 整改结果上报表格数据
      ReviseRecordList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        wbs: null,
        checkItem: null,
        checkTime: null,
        uploadTime: null,
        picNumber: null
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
    /** 查询整改结果上报列表 */
    getList() {

      // 当el-cascader的值变化时，更新queryParams.wbs为最后一级的值
      if (this.selectedWbs && this.selectedWbs.length > 0) {
        this.queryParams.wbs = this.selectedWbs[this.selectedWbs.length - 1];
      } else {
        this.queryParams.wbs = null;
      }



      this.loading = true;

      this.queryParams.params = {};
      if (null != this.daterangeUploadTime && '' != this.daterangeUploadTime) {
        this.queryParams.params["beginUploadTime"] = this.daterangeUploadTime[0];
        this.queryParams.params["endUploadTime"] = this.daterangeUploadTime[1];
      }

      listReviseRecord(this.queryParams).then(response => {
        this.ReviseRecordList = response.rows;
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
        reviseId: null,
        wbs: null,
        checkItem: null,
        questionDescription: null,
        photos: null,
        checkTime: null,
        uploadTime: null,
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
      this.ids = selection.map(item => item.reviseId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加整改结果上报";

      this.isadd =true;
      // 获取当前日期并转换为ISO字符串

      let utcTime =new Date();
      this.form.uploadTime = new Date(utcTime.getTime()+8 * 60 * 60 * 1000);
      this.form.uploadTime = this.form.uploadTime.toISOString();


      // 转换为 "yyyy-MM-dd HH:mm:ss" 格式
      this.form.uploadTime = this.form.uploadTime.substring(0, 10) + " " + this.form.uploadTime.substring(11, 19).replace("T", " ");

    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const reviseId = row.reviseId || this.ids
      getReviseRecord(reviseId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改整改结果上报";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {


          //只读取最后一级的数据
          if (this.form.wbs && this.form.wbs.length > 0) {
            this.form.wbs = this.form.wbs[this.form.wbs.length - 1];
          }


          if (this.form.reviseId != null) {
            updateReviseRecord(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addReviseRecord(this.form).then(response => {
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
      const reviseIds = row.reviseId || this.ids;
      this.$modal.confirm('是否确认删除整改结果上报编号为"' + reviseIds + '"的数据项？').then(function() {
        return delReviseRecord(reviseIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('ReviseRecord/ReviseRecord/export', {
        ...this.queryParams
      }, `ReviseRecord_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
