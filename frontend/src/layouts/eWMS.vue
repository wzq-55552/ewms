<template>
  <q-layout> <!-- Be sure to play with the Layout demo on docs -->

    <!-- (Optional) The Header -->

    <!-- (Optional) The Footer -->

    <!-- (Optional) A Drawer; you can add one more with side="right" or change this one's side -->

    <q-page-container>
     <template>
    <q-page>
            <!-- content -->
        <div class="content">
        <div class="wrap">
        <div class="form-data gt-sm inline">
        <login></login>
        </div>

        <div class="lt-md inline moblie">
        <login></login>
        </div>
</div>
</div>
  </q-page>
</template>
    </q-page-container>

  </q-layout>
</template>

<script>
import login from 'components/login'
export default {
  components: {
    login
  }
}
</script>
<style scoped>
@import url('../css/reg.css');
</style>
<style>
body,html{
  width: 100%;
  height: 100%;
}
.moblie #forgetpassword{
  margin: -30px 0 20px 0;
}
.content{
  width: 100%;
  height: 100%;
  background-color: #3895e8;
  padding: 16px;
}
</style>
