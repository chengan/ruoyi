<template>
  <div class="app-container">
    <el-form v-show="showSearch" ref="queryForm" :inline="true" :model="queryParams" label-width="68px" size="small">

      <el-form-item label="项目wbs" prop="wbs">
        <div class="block">
          <span class="demonstration"></span>
          <el-cascader
            v-model="searchSelectedWbs"
            :options="wbsOptions"
            :show-all-levels="false"
            :props="searchWbsProps"
            clearable
            @change="handleSearchWbsChange"
            :loading="wbsLoading"
          />
        </div>
      </el-form-item>

      <el-form-item label="项目id" prop="itemId">
        <el-input
          v-model="queryParams.itemId"
          clearable
          placeholder="请输入项目id"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目名称" prop="itemName">
        <el-input
          v-model="queryParams.itemName"
          clearable
          placeholder="请输入项目名称"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="检查项目" prop="checkItem">
        <el-input
          v-model="queryParams.checkItem"
          clearable
          placeholder="请输入检查项目"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="施工单位" prop="company">
        <el-input
          v-model="queryParams.company"
          clearable
          placeholder="请输入施工单位"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>


      <el-form-item label="检查结果" prop="checkResult">
        <!--        <el-input-->
        <!--          v-model="queryParams.checkResult"-->
        <!--          placeholder="请输入检查结果"-->
        <!--          clearable-->
        <!--          @keyup.enter.native="handleQuery"-->
        <!--        />-->

        <el-select v-model="queryParams.checkResult" placeholder="请选择">
          <el-option
            v-for="item in options1"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>

      </el-form-item>


      <el-form-item label="检查时间" prop="checkTime">
        <el-date-picker v-model="queryParams.checkTime"
                        clearable
                        placeholder="请选择检查时间"
                        type="date"
                        value-format="yyyy-MM-dd">
        </el-date-picker>
      </el-form-item>
<!--      <el-form-item label="上传时间" prop="uploadTime">-->
<!--        <el-date-picker v-model="queryParams.uploadTime"-->
<!--                        clearable-->
<!--                        placeholder="请选择上传时间"-->
<!--                        type="date"-->
<!--                        value-format="yyyy-MM-dd">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->


      <el-form-item label="上传时间">
        <el-date-picker
          v-model="daterangeUploadTime"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>


      <el-form-item label="负责人" prop="picNumber">
        <el-input
          v-model="queryParams.picNumber"
          clearable
          placeholder="请输入负责人"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button icon="el-icon-search" size="mini" type="primary" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['QualityCheckReport:QualityCheckReport:add']"
          icon="el-icon-plus"
          plain
          size="mini"
          type="primary"
          @click="handleAdd"
        >新增
        </el-button>
      </el-col>
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="success"-->
      <!--          plain-->
      <!--          icon="el-icon-edit"-->
      <!--          size="mini"-->
      <!--          :disabled="single"-->
      <!--          @click="handleUpdate"-->
      <!--          v-hasPermi="['QualityCheckReport:QualityCheckReport:edit']"-->
      <!--        >修改</el-button>-->
      <!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['QualityCheckReport:QualityCheckReport:remove']"
          :disabled="multiple"
          icon="el-icon-delete"
          plain
          size="mini"
          type="danger"
          @click="handleDelete"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          v-hasPermi="['QualityCheckReport:QualityCheckReport:export']"
          icon="el-icon-download"
          plain
          size="mini"
          type="warning"
          @click="handleExport"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="QualityCheckReportList" @selection-change="handleSelectionChange">
      <el-table-column align="center" type="selection" width="55"/>
      <el-table-column align="center" label="检查ID" prop="checkId"/>
      <el-table-column align="center" label="项目wbs" prop="wbs"/>
      <el-table-column align="center" label="项目id" prop="itemId"/>
      <el-table-column align="center" label="项目名称" prop="itemName"/>
      <el-table-column align="center" label="检查项目" prop="checkItem"/>
      <el-table-column align="center" label="施工单位" prop="company"/>
<!--      <el-table-column align="center" label="检查结果" prop="checkResult"/>-->
      <el-table-column   label="检查结果">
        <!-- 添加条件判断，如果审批结果为null，则不显示标签 -->
        <template slot-scope="scope">
          <el-tag v-if="scope.row.checkResult" :type="scope.row.checkResult === '合格' ? 'success' : 'danger'"
                  effect="dark" :color="scope.row.checkResult === '合格' ? '#A2BD99' : '#F08784'" :border="false">
            {{ scope.row.checkResult }}
          </el-tag>
        </template>
      </el-table-column>


      <el-table-column align="center" label="现场照片" prop="photo" width="100">
        <template slot-scope="scope">
          <image-preview :height="50" :src="scope.row.photo" :width="50"/>
        </template>
      </el-table-column>
      <el-table-column align="center" label="问题描述" prop="questionDescription"/>
      <el-table-column align="center" label="整改措施" prop="revise"/>
      <el-table-column align="center" label="检查时间" prop="checkTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="上传时间" prop="uploadTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.uploadTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" label="负责人" prop="picNumber"/>
      <el-table-column align="center" class-name="small-padding fixed-width" label="操作">
        <template slot-scope="scope">
          <el-button
            v-hasPermi="['QualityCheckReport:QualityCheckReport:edit']"
            icon="el-icon-edit"
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
          >修改
          </el-button>
          <el-button
            v-hasPermi="['QualityCheckReport:QualityCheckReport:remove']"
            icon="el-icon-delete"
            size="mini"
            type="text"
            @click="handleDelete(scope.row)"
          >删除
          </el-button>

          <el-button type="text" icon="el-icon-view" size="mini" @click="viewRecords(scope.row)">查看记录</el-button>


          <el-dialog title="整改记录详情" :visible.sync="dialogVisible">
            <el-table :data="records">
              <el-table-column prop="checkResult" label="是否合格">
                <template slot-scope="scope">
                  <el-tag v-if="scope.row.checkResult" :type="scope.row.checkResult === '合格' ? 'success' : 'danger'"
                          effect="dark" :color="scope.row.checkResult === '合格' ? '#A2BD99' : '#F08784'">
                    {{ scope.row.checkResult }}
                  </el-tag>
                </template>
              </el-table-column>
              <el-table-column prop="checkTime" label="检查时间" ></el-table-column>
              <el-table-column prop="questionDescription" label="问题描述" ></el-table-column>
              <el-table-column prop="revise" label="整改措施" ></el-table-column>
            </el-table>
          </el-dialog>


        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :limit.sync="queryParams.pageSize"
      :page.sync="queryParams.pageNum"
      :total="total"
      @pagination="getList"
    />

    <!-- 添加或修改施工质量上报对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body width="500px">
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目wbs" prop="wbs">
          <el-cascader
            v-model="selectedWbs"
            :options="wbsOptions"
            :props="wbsProps"
            clearable
            filterable
            :show-all-levels="false"
            @change="handleWbsChange"
            placeholder="请选择项目WBS"
            :loading="wbsLoading"
          />
        </el-form-item>
        <el-form-item label="项目id" prop="itemId">
          <el-input v-model="form.itemId" placeholder="请输入项目id" :readonly="true"/>
        </el-form-item>
        <el-form-item label="项目名称" prop="itemName">
          <el-input v-model="form.itemName" placeholder="请输入项目名称"/>
        </el-form-item>
        <el-form-item label="检查项目" prop="checkItem">
          <el-input v-model="form.checkItem" placeholder="请输入检查项目"/>
        </el-form-item>
        <el-form-item label="施工单位" prop="company">
          <el-input v-model="form.company" placeholder="请输入施工单位"/>
        </el-form-item>
        <el-form-item label="检查结果" prop="checkResult">

          <el-select v-model="form.checkResult" placeholder="请选择">
            <el-option
              v-for="item in options1"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>

          <!--          <el-input v-model="form.checkResult" placeholder="请输入检查结果" />-->
        </el-form-item>
        <el-form-item label="现场照片" prop="photo">
          <image-upload v-model="form.photo"/>
        </el-form-item>
        <el-form-item label="问题描述" prop="questionDescription">
          <el-input v-model="form.questionDescription" placeholder="请输入内容" type="textarea"/>
        </el-form-item>
        <el-form-item label="整改措施" prop="revise">
          <el-input v-model="form.revise" placeholder="请输入内容" type="textarea"/>
        </el-form-item>
        <el-form-item label="检查时间" prop="checkTime">
          <el-date-picker v-model="form.checkTime"
                          clearable
                          placeholder="请选择检查时间"
                          type="date"
                          value-format="yyyy-MM-dd">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="上传时间" prop="uploadTime">
          <el-date-picker v-model="form.uploadTime"
                          clearable
                          placeholder="上传时间"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
                          :disabled="isadd">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="负责人" prop="picNumber">
          <el-input v-model="form.picNumber" placeholder="请输入负责人"/>
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
import {
  listQualityCheckReport,
  getQualityCheckReport,
  delQualityCheckReport,
  addQualityCheckReport,
  updateQualityCheckReport,
  getWbsList
} from "@/api/QualityCheckReport/QualityCheckReport";
import axios from 'axios';

import { listCheckViews, getCheckViews, delCheckViews, addCheckViews, updateCheckViews } from "@/api/CheckViews/CheckViews";
import {listCheckViews1} from "@/api/CheckViews1/CheckViews1";


export default {
  name: "QualityCheckReport",
  data() {
    return {
      selectedWbs: null,
      searchSelectedWbs: null,

      dialogVisible: false, // 控制对话框显示
      records: [], // 用于存储记录的数组

      checkViews: [],




      isadd: false,

      options1: [{
        value: '合格',
        label: '合格'
      }, {
        value: '整改',
        label: '整改'
      }],
      checkResult: '',


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
        },]
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
      // 施工质量上报表格数据
      QualityCheckReportList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        wbs: null,
        itemId: null,
        itemName: null,
        checkItem: null,
        company: null,
        checkResult: null,
        checkTime: null,
        uploadTime: null,
        picNumber: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
      // WBS选择器相关配置
      wbsOptions: [],
      wbsProps: {
        multiple: false,
        value: 'id',
        label: 'wbs',
        children: 'children',
        checkStrictly: true,
        emitPath: false,
        expandTrigger: 'hover'
      },
      wbsLoading: false,
      // 搜索栏WBS选择器配置
      searchWbsProps: {
        multiple: false,
        value: 'wbs',// 直接使用wbs字段作为value
        label: 'wbs',// 显示也使用wbs字段
        children: 'children',
        checkStrictly: true,
        emitPath: false,
        expandTrigger: 'hover'
      },
    };
  },
  created() {
    this.getWbsOptions();
    this.getList();
  },
  methods: {
    load(){
      selectQualityCheckReport.then(response => {
        console.log(response)
      });
    },
/*    /!** 处理最后一级数据*!/
    handleCascaderChange(value, selectedData) {
      // 提取最后一级的数据
      const lastLevelData = selectedData[selectedData.length - 1].value;
      // 发送数据到后端
      this.sendDataToBackend(lastLevelData);
    },*/




    // viewRecords() {
    //   // 模拟获取本地数据
    //   this.records = this.getRecordsFromLocal();
    //
    //   // 显示对话框
    //   this.dialogVisible = true;
    // },
    // getRecordsFromLocal() {
    //   // 模拟从本地获取记录
    //   return [
    //     { wbs: 'WBS001', views: 100 },
    //     { wbs: 'WBS002', views: 200 },
    //     { wbs: 'WBS003', views: 300 },
    //     // 更多记录...
    //   ];
    // },





    // viewRecords() {
    //   // 从 localStorage 获取记录
    //   this.records = JSON.parse(localStorage.getItem('check_views')) || [];
    //
    //   // 显示对话框
    //   this.dialogVisible = true;
    // },

/**详情按钮*/




viewRecords(row) {
  this.reset();
  const wbs = row.wbs || this.ids
  listQualityCheckReport({wbs: wbs}).then(response => {
    console.log(response.rows);
    this.records = response.rows;
    this.dialogVisible = true;
    // this.title = "修改CheckViews";有种聋哑人交流的感觉
  });
},





    /** 查询施工质量上报列表 */
    getList() {
      this.loading = true
      this.queryParams.params = {}

      // 直接使用选中的wbs值
      if (this.searchSelectedWbs) {
        this.queryParams.wbs = this.searchSelectedWbs
      } else {
        this.queryParams.wbs = null
      }

      // 处理时间范围
      if (this.daterangeUploadTime != null && this.daterangeUploadTime !== '') {
        this.queryParams.params.beginUploadTime = this.daterangeUploadTime[0]
        this.queryParams.params.endUploadTime = this.daterangeUploadTime[1]
      }

      // 发送查询请求
      listQualityCheckReport(this.queryParams).then(response => {
        this.QualityCheckReportList = response.rows
        this.total = response.total
        this.loading = false
      })
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
        wbs: null,
        itemId: null,
        itemName: null,
        checkItem: null,
        company: null,
        checkResult: null,
        photo: null,
        questionDescription: null,
        revise: null,
        checkTime: null,
        uploadTime: null,
        picNumber: null
      }
      this.selectedWbs = null  // 添加这行，重置WBS选择器的值
      this.resetForm("form")
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
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.getWbsOptions();  // 获取WBS选项数据
      this.open = true;
      this.title = "添加施工质量上报";

      // 获取当前日期并转换为ISO字符串

      let utcTime =new Date();
      this.form.uploadTime = new Date(utcTime.getTime()+8 * 60 * 60 * 1000);
      this.form.uploadTime = this.form.uploadTime.toISOString();


    // 转换为 "yyyy-MM-dd HH:mm:ss" 格式
      this.form.uploadTime = this.form.uploadTime.substring(0, 10) + " " + this.form.uploadTime.substring(11, 19).replace("T", " ");

      this.isadd =true;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      this.isadd = true
      this.getWbsOptions()  // 获取WBS选项数据
      const checkId = row.checkId || this.ids
      getQualityCheckReport(checkId).then(response => {
        this.form = response.data
        
        // 添加以下代码，设置WBS选择器的值
        const findNodeByWbs = (nodes, targetWbs) => {
          if (!nodes) return null
          for (const node of nodes) {
            if (node.wbs === targetWbs) {
              return node.id
            }
            if (node.children) {
              const found = findNodeByWbs(node.children, targetWbs)
              if (found) return found
            }
          }
          return null
        }
        
        // 设置WBS选择器的值
        this.selectedWbs = findNodeByWbs(this.wbsOptions, this.form.wbs)
        
        this.open = true
        this.title = "修改施工质量上报"
      })
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          // 不需要额外处理 wbs，直接使用 form 中的值
          if (this.form.checkId != null) {
            updateQualityCheckReport(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addQualityCheckReport(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除施工质量上报编号为"' + checkIds + '"的数据项？').then(function () {
        return delQualityCheckReport(checkIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('QualityCheckReport/QualityCheckReport/export', {
        ...this.queryParams
      }, `QualityCheckReport_${new Date().getTime()}.xlsx`)
    },
    /** 获取WBS列表数据 */
    getWbsOptions() {
      this.wbsLoading = true
      getWbsList().then(response => {
        this.wbsOptions = response.data;
        this.wbsLoading = false;
      });
    },
    /** WBS选择改变时的处理函数 */
    handleWbsChange(value) {
      if (value) {
        try {
          const findSelectedNode = (nodes, targetId) => {
            if (!nodes) return null;
            for (let node of nodes) {
              if (node.id === targetId) {
                return node;
              }
              if (node.children) {
                const found = findSelectedNode(node.children, targetId);
                if (found) return found;
              }
            }
            return null;
          };

          const selectedData = findSelectedNode(this.wbsOptions, value);
          if (selectedData) {
            this.form.wbs = selectedData.wbs;
            this.form.itemName = selectedData.taskName;
            this.form.itemId = selectedData.id;
          } else {
            this.$message.error('未找到选中的WBS数据');
          }
        } catch (error) {
          console.error('WBS选择处理出错:', error);
          this.$message.error('WBS选择处理出错');
        }
      } else {
        // 当值被清空时，同步清空相关字段
        this.form.wbs = null;
        this.form.itemName = null;
        this.form.itemId = null;
      }
    },
    /** 搜索栏WBS选择改变时的处理函数 */
    handleSearchWbsChange(value) {
      this.getList()
    },
  }
};
</script>
