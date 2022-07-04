<template xmlns:v-slot="http://www.w3.org/1999/XSL/Transform">
  <a-layout>
    <a-layout-content
            :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '280px' }"
    >
      <a-table
              :columns="columns"
              :row-key="record => record.id"
              :data-source="ebooks"
              :pagination="pagination"
              :loading="loading"
              @change="handleTableChange"
      >
        <template #cover="{ text: cover }">
          <img v-if="cover" :src="cover" alt="avatar" />
        </template>
        <template v-slot:action="{ text, record }">
          <a-space size="small">
            <a-button type="primary" @click="edit(record)">
              edit
            </a-button>
            <a-button type="danger">
              delete
            </a-button>
          </a-space>
        </template>
      </a-table>
    </a-layout-content>
  </a-layout>

  <a-modal
          title="ebook form"
          v-model:visible="modalVisible"
          :confirm-loading="modalLoading"
          @ok="handleModalOk"
  >
    <a-form :model="ebook" :label-col="{ span: 6 }" :wrapper-col="{ span: 18 }">
      <a-form-item label="cover">
        <a-input v-model:value="ebook.cover" />
      </a-form-item>
      <a-form-item label="name">
        <a-input v-model:value="ebook.name" />
      </a-form-item>
      <a-form-item label="category1">
        <a-input v-model:value="ebook.category1Id" />
      </a-form-item>
      <a-form-item label="category2">
        <a-input v-model:value="ebook.category2Id" />
      </a-form-item>
      <a-form-item label="description">
        <a-input v-model:value="ebook.desc" type="textarea" />
      </a-form-item>
    </a-form>
  </a-modal>
</template>

<script lang="ts">
  import { defineComponent, onMounted, ref } from 'vue';
  import axios from 'axios';

  export default defineComponent({
    name: 'AdminEbook',
    setup() {
      const ebooks = ref();
      const pagination = ref({
        current: 1,
        pageSize: 4,
        total: 0
      });
      const loading = ref(false);

      const columns = [
        {
          title: 'cover',
          dataIndex: 'cover',
          slots: { customRender: 'cover' }
        },
        {
          title: 'name',
          dataIndex: 'name'
        },
        {
          title: 'category1',
          key: 'category1Id',
          dataIndex: 'category1Id'
        },
        {
          title: 'category2',
          dataIndex: 'category2Id'
        },
        {
          title: 'doccount',
          dataIndex: 'docCount'
        },
        {
          title: 'viewcount',
          dataIndex: 'viewCount'
        },
        {
          title: 'like',
          dataIndex: 'voteCount'
        },
        {
          title: 'Action',
          key: 'action',
          slots: { customRender: 'action' }
        }
      ];

      /**
       * data query
       **/
      const handleQuery = (params: any) => {
        loading.value = true;
        axios.get("/ebook/list", {
          params: {
            page: params.page,
            size: params.size
          }
        }).then((response) => {
          loading.value = false;
          const data = response.data;
          ebooks.value = data.content.list;

          // reset the pagination
          pagination.value.current = params.page;
          pagination.value.total = data.content.total;
        });
      };

      /**
       * when click the page num trigger
       */
      const handleTableChange = (pagination: any) => {
        console.log("what is the pagination parameter：" + pagination);
        handleQuery({
          page: pagination.current,
          size: pagination.pageSize
        });
      };

      // -------- form ---------
      const ebook = ref({});
      const modalVisible = ref(false);
      const modalLoading = ref(false);
      const handleModalOk = () => {
        modalLoading.value = true;
        setTimeout(() => {
          modalVisible.value = false;
          modalLoading.value = false;
        }, 2000);
      };

      /**
       * edit
       */
      const edit = (record: any) => {
        modalVisible.value = true;
        ebook.value = record
      };

      onMounted(() => {
        handleQuery({
          page: 1,
          size: pagination.value.pageSize,
        });
      });

      return {
        ebooks,
        pagination,
        columns,
        loading,
        handleTableChange,

        edit,

        ebook,
        modalVisible,
        modalLoading,
        handleModalOk
      }
    }
  });
</script>

<style scoped>
  img {
    width: 50px;
    height: 50px;
  }
</style>
