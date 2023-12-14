<template>
  <div class="about">
    <the-header></the-header>
    <the-index></the-index>
    <the-footer></the-footer>
  </div>
</template>
<script>
import TheHeader from "@/components/about/the-header";
import TheFooter from "@/components/about/the-footer";
import TheIndex from "@/components/about/the-index";
export default {
  name: 'about',
  components: {TheIndex, TheFooter, TheHeader},

}
</script>