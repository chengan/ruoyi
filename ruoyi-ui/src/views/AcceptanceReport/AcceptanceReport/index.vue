<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目wbs" prop="wbs">
        <div class="block">
          <span class="demonstration" />
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
          placeholder="请输入项目id"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目名称" prop="itemName">
        <el-input
          v-model="queryParams.itemName"
          placeholder="请输入项目名称"
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
      <el-form-item label="施工单位" prop="company">
        <el-input
          v-model="queryParams.company"
          placeholder="请输入施工单位"
          clearable
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
        <el-date-picker clearable
          v-model="queryParams.checkTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择检查时间">
        </el-date-picker>
      </el-form-item>
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
          v-hasPermi="['AcceptanceReport:AcceptanceReport:add']"
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
          v-hasPermi="['AcceptanceReport:AcceptanceReport:edit']"
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
          v-hasPermi="['AcceptanceReport:AcceptanceReport:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['AcceptanceReport:AcceptanceReport:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="AcceptanceReportList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="验收ID" align="center" prop="acceptanceId" />
      <el-table-column label="项目wbs" align="center" prop="wbs" />
      <el-table-column label="项目id" align="center" prop="itemId" />
      <el-table-column label="项目名称" align="center" prop="itemName" />
      <el-table-column label="检查项目" align="center" prop="checkItem" />
      <el-table-column label="施工单位" align="center" prop="company" />
<!--      <el-table-column label="检查结果" align="center" prop="checkResult" />-->

      <el-table-column   label="检查结果">
        <!-- 添加条件判断，如果审批结果为null，则不显示标签 -->
        <template slot-scope="scope">
          <el-tag v-if="scope.row.checkResult" :type="scope.row.checkResult === '合格' ? 'success' : 'danger'"
                  effect="dark">{{ scope.row.checkResult }}
          </el-tag>
        </template>
      </el-table-column>


      <el-table-column label="现场照片" align="center" prop="photo" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.photo" :width="50" :height="50"/>
        </template>
      </el-table-column>
      <el-table-column label="问题描述" align="center" prop="questionDescription" />
      <el-table-column label="整改措施" align="center" prop="revise" />
      <el-table-column label="检查时间" align="center" prop="checkTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="上传时间" align="center" prop="uploadTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.uploadTime, '{y}-{m}-{d} {h}:{i}:{s}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="负责人" align="center" prop="picNumber" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['AcceptanceReport:AcceptanceReport:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['AcceptanceReport:AcceptanceReport:remove']"
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

    <!-- 添加或修改竣工验收上报对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
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
          <el-input v-model="form.itemId" placeholder="请输入项目id" />
        </el-form-item>
        <el-form-item label="项目名称" prop="itemName">
          <el-input v-model="form.itemName" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="检查项目" prop="checkItem">
          <el-input v-model="form.checkItem" placeholder="请输入检查项目" />
        </el-form-item>
        <el-form-item label="施工单位" prop="company">
          <el-input v-model="form.company" placeholder="请输入施工单位" />
        </el-form-item>
        <el-form-item label="检查结果" prop="checkResult">
<!--          <el-input v-model="form.checkResult" placeholder="请输入检查结果" />-->
          <el-select v-model="form.checkResult" placeholder="请选择">
            <el-option
              v-for="item in options1"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>

        </el-form-item>
        <el-form-item label="现场照片" prop="photo">
          <image-upload v-model="form.photo"/>
        </el-form-item>
        <el-form-item label="问题描述" prop="questionDescription">
          <el-input v-model="form.questionDescription" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="整改措施" prop="revise">
          <el-input v-model="form.revise" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="检查时间" prop="checkTime">
          <el-date-picker clearable
            v-model="form.checkTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择检查时间">
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
import { listAcceptanceReport, getAcceptanceReport, delAcceptanceReport, addAcceptanceReport, updateAcceptanceReport } from "@/api/AcceptanceReport/AcceptanceReport";
import { getWbsList } from '@/api/QualityCheckReport/QualityCheckReport'

export default {
  name: "AcceptanceReport",
  data() {
    return {
      selectedWbs: null,        // 新增/修改弹窗使用
      searchSelectedWbs: null,  // 搜索栏使用
      wbsOptions: [],          // WBS树形数据
      wbsLoading: false,
      
      // 搜索栏WBS选择器配置
      searchWbsProps: {
        multiple: false,
        value: 'wbs',
        label: 'wbs',
        children: 'children',
        checkStrictly: true,
        emitPath: false,
        expandTrigger: 'hover'
      },
      
      // 新增/修改弹窗WBS选择器配置
      wbsProps: {
        multiple: false,
        value: 'id',
        label: 'wbs',
        children: 'children',
        checkStrictly: true,
        emitPath: false,
        expandTrigger: 'hover'
      },
      
      isadd: false,
      selectedWbs: null,//用于记录返回最后一级的数据

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
      // 竣工验收上报表格数据
      AcceptanceReportList: [],
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
      rules: {
      }
    };
  },
  created() {
    this.getWbsOptions()
    this.getList()
  },
  methods: {
    /** 获取WBS列表数据 */
    getWbsOptions() {
      this.wbsLoading = true
      getWbsList().then(response => {
        this.wbsOptions = response.data
        this.wbsLoading = false
      })
    },

    /** 搜索栏WBS选择改变时的处理函数 */
    handleSearchWbsChange(value) {
      this.getList()
    },

    /** WBS选择改变时的处理函数 */
    handleWbsChange(value) {
      if (value) {
        try {
          const findSelectedNode = (nodes, targetId) => {
            if (!nodes) return null
            for (const node of nodes) {
              if (node.id === targetId) {
                return node
              }
              if (node.children) {
                const found = findSelectedNode(node.children, targetId)
                if (found) return found
              }
            }
            return null
          }

          const selectedData = findSelectedNode(this.wbsOptions, value)
          if (selectedData) {
            this.form.wbs = selectedData.wbs
            this.form.itemName = selectedData.taskName
            this.form.itemId = selectedData.id
          }
        } catch (error) {
          console.error('WBS选择处理出错:', error)
        }
      } else {
        this.form.wbs = null
        this.form.itemName = null
        this.form.itemId = null
      }
    },

    /** 查询竣工验收上报列表 */
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

      listAcceptanceReport(this.queryParams).then(response => {
        this.AcceptanceReportList = response.rows
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
        acceptanceId: null,
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
      this.ids = selection.map(item => item.acceptanceId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加竣工验收上报";
      this.isadd = true;

      //获取当前日期并转换为ISO字符串,并将时间向后延长8小时
      let utcTime =new Date();
      this.form.uploadTime = new Date(utcTime.getTime()+8 * 60 * 60 * 1000);
      this.form.uploadTime = this.form.uploadTime.toISOString();


      // 转换为 "yyyy-MM-dd HH:mm:ss" 格式
      this.form.uploadTime = this.form.uploadTime.substring(0, 10) + " " + this.form.uploadTime.substring(11, 19).replace("T", " ");

    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset()
      const acceptanceId = row.acceptanceId || this.ids
      getAcceptanceReport(acceptanceId).then(response => {
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
        this.title = "修改竣工验收上报"
      })
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.acceptanceId != null) {
            updateAcceptanceReport(this.form).then(response => {
              this.$modal.msgSuccess("修改成功")
              this.open = false
              this.getList()
            })
          } else {
            addAcceptanceReport(this.form).then(response => {
              this.$modal.msgSuccess("新增成功")
              this.open = false
              this.getList()
            })
          }
        }
      })
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const acceptanceIds = row.acceptanceId || this.ids;
      this.$modal.confirm('是否确认删除竣工验收上报编号为"' + acceptanceIds + '"的数据项？').then(function() {
        return delAcceptanceReport(acceptanceIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('AcceptanceReport/AcceptanceReport/export', {
        ...this.queryParams
      }, `AcceptanceReport_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
