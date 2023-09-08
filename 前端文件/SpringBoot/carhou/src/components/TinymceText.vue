<template>
  <div>
<!--    <textarea id="myTextarea" ref="myTextarea"></textarea>-->
    <editor v-model="myValue"
      :init="init"

    >

    </editor>
  </div>

</template>


<script>
import tinymce from 'tinymce/tinymce'
import Editor from '@tinymce/tinymce-vue'
import 'tinymce/themes/silver/theme' // 主题文件
import 'tinymce/icons/default'

import '../assets/js/langs/zh-Hans';
import "../assets/js/skins/ui/oxide/skin.min.css"
import "../assets/js/skins/content/default/content.min.css"
import "../assets/js/skins/content/default/content.css"
import 'tinymce/themes/silver/theme';


//插件引入
import 'tinymce/plugins/anchor'
import 'tinymce/plugins/link'
import 'tinymce/plugins/advlist'
import 'tinymce/plugins/lists'
import 'tinymce/plugins/table'
import 'tinymce/plugins/fullscreen'
import 'tinymce/plugins/help'
import 'tinymce/plugins/image'
import 'tinymce/plugins/charmap'
import 'tinymce/plugins/hr'
import 'tinymce/plugins/preview'
import 'tinymce/plugins/quickbars'
import 'tinymce/plugins/autolink'
import 'tinymce/plugins/paste'
import 'tinymce/plugins/print'
import 'tinymce/plugins/save'
import 'tinymce/plugins/searchreplace'
import 'tinymce/plugins/nonbreaking'

import 'tinymce/plugins/wordcount'

export default {
  name: "TinymceText",
  components: {
    Editor
  },
  props: {
    // 默认的富文本内容
    value: {
      type: String,
      default: ''
    },
    // 基本路径，默认为空根目录，如果你的项目发布后的地址为目录形式，
    // 即abc.com/tinymce，baseUrl需要配置成tinymce，不然发布后资源会找不到
    baseUrl: {
      type: String,
      default: window.location.origin ? window.location.origin : ''
    },
    // 禁用
    disabled: {
      type: Boolean,
      default: false
    },
    plugins: {
      type: [String, Array],
      default: 'anchor link lists advlist table fullscreen help image charmap hr  preview  quickbars autolink paste print save ' +
          'searchreplace,nonbreaking  wordcount'
    },
    toolbar: {
      type: [String, Array],
      default: ' undo redo | cut copy paste pastetext| forecolor backcolor bold italic underline strikethrough link anchor | alignleft aligncenter alignright alignjustify outdent indent  bullist numlist |  \
        styleselect formatselect fontselect fontsizeselect | blockquote subscript superscript removeformat | table fullscreen help | \n \ \
        image charmap hr | preview  paste print | save searchreplace nonbreaking   wordcount'
    }

  },
  data() {
    return {
      init:{
        height: 500,
        width: 1000,
        language: 'zh_CN',
        branding: false,
        // 隐藏菜单栏
        menubar: false,
        // 是否显示底部状态栏
        statusbar: true,
        selector: '#myTextarea',

      },
      myValue: this.value
    }
  },


  mounted() {
    // tinymce.init({
    //   selector: '#myTextarea',
    //   height: 500,
    //   width: 1000,
    //   branding: false,
    //   // 隐藏菜单栏
    //   menubar: false,
    //   // 是否显示底部状态栏
    //   statusbar: true,
    //   language: 'zh-Hans',
    //   plugins: [
    //     'anchor link lists advlist table fullscreen help image charmap hr  preview  quickbars autolink paste print save ' +
    //     'searchreplace,nonbreaking  wordcount'
    //   ],
    //
    //   toolbar: [
    //
    //     ' undo redo | cut copy paste pastetext| forecolor backcolor bold italic underline strikethrough link anchor | alignleft aligncenter alignright alignjustify outdent indent  bullist numlist |  \
    //     styleselect formatselect fontselect fontsizeselect | blockquote subscript superscript removeformat | table fullscreen help | \n \ \
    //     image charmap hr | preview  paste print | save searchreplace nonbreaking   wordcount'
    //
    //   ],
    //
    //   toolbar_sticky: true,
    //   autosave_ask_before_unload: false,
    //   setup: editor => {
    //
    //   }
    //
    // })
    tinymce.init({})

  },
  beforeUnmount() {
    // tinymce.remove('#' + this.$refs.myTextarea.id);
    tinymce.remove('#myTextarea')
  }
}
</script>

<style scoped>

</style>