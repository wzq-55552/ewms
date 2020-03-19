<template>
<div>
<div class="q-pa-md">
   <div class="fit">
    <q-carousel
      swipeable
      animated
      v-model="slide"
      thumbnails
      infinite
       autoplay
       height="20rem"
    >
      <q-carousel-slide :name="1" img-src="statics/slide1.jpg" />
      <q-carousel-slide :name="2" img-src="statics/slide2.jpg" />
      <q-carousel-slide :name="3" img-src="statics/slide3.jpg" />
      <q-carousel-slide :name="4" img-src="statics/slide4.jpg" />
    </q-carousel>
    </div>
  </div>
  <div class="row justify-center   gt-sm inline relative-position" style="left:50%; transform: translate(-50%);">
  <a href="javascript:void(0);" style="margin-right:1rem;">帮助</a>
  <a href="javascript:void(0);">反馈</a>
  <a href="javascript:void(0);" style="margin-left:1rem;">联系我们</a>
  </div>
</div>

</template>
<style>
@media only screen and (min-width: 320px){
    html {
     font-size: 62.5% !important;
   }
 }
  @media only screen and (min-width: 640px){
    html {
      font-size: 125% !important;
    }
 }
 @media only screen and (min-width: 750px){
   html {
     font-size: 150% !important;
   }
 }
 @media only screen and (min-width: 1242px){
   html {
     font-size: 187.5% !important;
   }
 }
 a{
   text-decoration: none;
 }
</style>
<script>
import jsonp from 'jsonp'
import axios from 'axios'
export default {
  // name: 'ComponentName',
  data () {
    return {
      slide: 1
    }
  }
}
</script>

