<template>
  <div>
    <div v-for="item in params" :key="item.value"
         style="display: flex; align-items: center; align-content: space-between; gap: 20px; padding: 8px;">
      <b>{{ item.label }}</b>
      <div style="display: flex; gap: 20px;">
        <el-text v-for="i in paramList[item.value]" :key="i.value" @click="handleClick(item, i)"
                 :type="queryParams[item.value] === i.value ? 'danger' : 'info'" tag="b" style="cursor: pointer;">
          {{ i.label }}
        </el-text>
      </div>
    </div>
    <el-row>
      <el-col v-for="(item, index) in carList" :key="index" :xs="12" :sm="12" :md="8" :lg="6" :xl="6"
              style="padding: 8px;">
        <el-card>
          <img src="@/assets/imgs/car/1.jpg" alt="">
          <div style="display: flex; align-content: space-between; padding: 16px">
            <div>
              <p>飞度两厢1.5-CVT潮跑+型(国Ⅴ)</p>
              <p>
                价格:
                <el-text size="large" style="color: var(--el-color-black); font-weight: bold">7.88</el-text>
                万
              </p>
              <div style="display: flex; gap: 8px;">
                <el-tag class="black-tag">甄选好车</el-tag>
                <el-tag class="gray-tag">2.70万公里</el-tag>
                <el-tag class="gray-tag">2019年</el-tag>
              </div>
            </div>
            <div>
              <img src="@/assets/logo.jpg" alt="">
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref } from 'vue'

const params = ref([
  { label: '类别', value: 'fuel_type' },
  { label: '品牌', value: 'brand' },
  { label: '价格', value: 'price' }
])
const paramList = ref({
  fuel_type: [
    { label: '全部', value: null },
    { label: '汽油', value: 1 },
    { label: '柴油', value: 2 },
    { label: '电动', value: 3 },
    { label: '混动', value: 4 },
    { label: '其他', value: 5 }
  ],
  brand: [
    { label: '全部', value: null },
    { label: '红旗', value: '红旗' },
    { label: '北京吉普', value: '北京吉普' },
    { label: '奥迪', value: '奥迪' },
    { label: '奥克斯', value: '奥克斯' },
    { label: '北汽', value: '北汽' },
    { label: '比亚迪', value: '比亚迪' },
    { label: '长安福特', value: '长安福特' },
    { label: '长城汽车', value: '长城汽车' }
  ],
  price: [
    { label: '全部', value: null },
    { label: '5万以下', value: 1 },
    { label: '5-10万', value: 2 },
    { label: '10-15万', value: 3 },
    { label: '15-20万', value: 4 },
    { label: '20万以上', value: 5 }
  ]
})
const queryParams = ref({
  fuel_type: null,
  brand: null,
  price: null
})
const carList = ref([
  {},
  {},
  {},
  {},
  {},
  {},
  {},
  {},
  {},
  {},
  {},
  {},
  {},
  {}
])
const handleClick = (item, i) => {
  queryParams.value[item.value] = i.value
}
</script>

<style scoped lang="scss">
.el-card {
  :deep(.el-card__body) {
    padding: 0;

    img {
      width: 100%;
    }

    .el-tag {
      color: var(--el-color-white);
    }

    .black-tag {
      background-color: var(--el-color-black);
    }

    .gray-tag {
      background-color: #9ca3af;
    }
  }
}
</style>
