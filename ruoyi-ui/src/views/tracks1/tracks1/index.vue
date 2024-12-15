<template>
  <div class="app-container">
    <div class="calendar-container">
      <el-calendar v-model="currentDate">
        <template slot="dateCell" slot-scope="{data}">
          <div class="calendar-day" @click="handleDateClick(data)">
            <span>{{ data.day.split('-').slice(-1)[0] }}</span>
            <div class="status-icons">
              <template v-if="dateData[data.day]">
                <span v-if="hasPassResult(dateData[data.day])" class="status-icon success">✅</span>
                <span v-if="hasReformResult(dateData[data.day])" class="status-icon error">❌</span>
              </template>
            </div>
          </div>
        </template>
      </el-calendar>
    </div>

    <!-- 日期详情弹窗 -->
    <el-dialog title="工作内容详情" :visible.sync="dialogVisible" width="80%">
      <el-table v-loading="loading" :data="currentDateData">
        <el-table-column label="项目wbs" align="center" prop="wbs" />
        <el-table-column label="项目id" align="center" prop="itemId" />
        <el-table-column label="检查项目" align="center" prop="checkItem" />
        <el-table-column label="施工单位" align="center" prop="company" />
        <el-table-column label="检查结果" align="center">
          <template slot-scope="scope">
            <el-tag
              v-if="scope.row.result"
              :type="getResultType(scope.row.result)"
              effect="dark"
            >
              {{ scope.row.result }}
            </el-tag>
            <span v-else>--</span>
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
        <el-table-column label="负责人" align="center" prop="picNumber" />
      </el-table>
      <pagination
        v-show="total > 0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </el-dialog>
  </div>
</template>

<script>
import { listTracks1, listTracks1ByDateRange } from '@/api/tracks1/tracks1'
import Pagination from '@/components/Pagination'

export default {
  name: 'Tracks1',
  components: {
    Pagination
  },
  data() {
    return {
      loading: false,
      currentDate: new Date(),
      dateData: {},
      dialogVisible: false,
      currentDateData: [],
      total: 0,
      currentCheckTime: '',
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        checkTime: undefined
      }
    }
  },

  created() {
    this.getCalendarData()
  },

  methods: {
    hasPassResult(items) {
      return items.some(item => item.result === '合格')
    },

    hasReformResult(items) {
      return items.some(item => item.result === '整改')
    },

    handleDateClick(data) {
      this.dialogVisible = true
      this.loading = true
      this.currentCheckTime = data.day
      this.queryParams.checkTime = data.day
      this.queryParams.pageNum = 1
      this.getList()
    },

    getList() {
      this.loading = true
      listTracks1(this.queryParams).then(response => {
        this.currentDateData = response.rows
        this.total = response.total
        this.loading = false
      }).catch(() => {
        this.loading = false
        this.$message.error('获取日期数据失败')
      })
    },

    parseTime(time, format) {
      if (!time) return ''
      const date = new Date(time)
      const year = date.getFullYear()
      const month = (date.getMonth() + 1).toString().padStart(2, '0')
      const day = date.getDate().toString().padStart(2, '0')
      return format.replace(/{y}/g, year).replace(/{m}/g, month).replace(/{d}/g, day)
    },

    getResultType(result) {
      return result === '合格' ? 'success' : 'danger'
    },

    async getCalendarData() {
      this.loading = true
      const currentDate = new Date(this.currentDate)
      const year = currentDate.getFullYear()
      const month = currentDate.getMonth() + 1
      const startDate = `${year}-${month.toString().padStart(2, '0')}-01`
      const lastDay = new Date(year, month, 0).getDate()
      const endDate = `${year}-${month.toString().padStart(2, '0')}-${lastDay}`

      try {
        const response = await listTracks1ByDateRange(startDate, endDate)
        const newDateData = {}
        response.rows.forEach(item => {
          const dateStr = this.parseDate(item.checkTime)
          if (!newDateData[dateStr]) {
            newDateData[dateStr] = []
          }
          newDateData[dateStr].push(item)
        })
        this.dateData = newDateData
      } catch (error) {
        this.$message.error('获取日历数据失败')
      } finally {
        this.loading = false
      }
    },

    parseDate(dateStr) {
      const date = new Date(dateStr)
      const year = date.getFullYear()
      const month = (date.getMonth() + 1).toString().padStart(2, '0')
      const day = date.getDate().toString().padStart(2, '0')
      return `${year}-${month}-${day}`
    }
  },

  watch: {
    currentDate() {
      this.getCalendarData()
    }
  }
}
</script>

<style scoped>
.calendar-container {
  margin: 20px;
}

.calendar-day {
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  cursor: pointer;
}

.status-icons {
  display: flex;
  flex-wrap: wrap;
  gap: 4px;
  margin-top: 4px;
  justify-content: center;
}

.status-icon {
  font-size: 12px;
}

.status-icon.success {
  color: #67C23A;
}

.status-icon.error {
  color: #F56C6C;
}

::v-deep .el-calendar-day {
  height: 100px;
  padding: 4px;
}
</style>
