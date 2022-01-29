const baseForm = {
   name: "1",
   time: null,
 }
 export default {
   name: "",
   data() {
     return {
       pickerOptions: {
         shortcuts: [{
           text: '本月',
           onClick(picker) {
             picker.$emit('pick', [new Date(), new Date()]);
           }
         }, {
           text: '今年至今',
           onClick(picker) {
             const end = new Date();
             const start = new Date(new Date().getFullYear(), 0);
             picker.$emit('pick', [start, end]);
           }
         }, {
           text: '最近六个月',
           onClick(picker) {
             const end = new Date();
             const start = new Date();
             start.setMonth(start.getMonth() - 6);
             picker.$emit('pick', [start, end]);
           }
         }],
       },
       dialogFormVisible: false,
       loading: null,
       form: {
         ...baseForm
       }
     };
   },
   methods: {
     init() {
       this.dialogFormVisible = true;
     },
     save(formName) {
       let that = this;
       that.$refs[formName].validate((valid) => {
         if (valid) {
           that.loading = true;
           setTimeout(function() {
             that.loading = false;
             that.$parent.init();
             that.dialogFormVisible = false;
             that.$notify({
               title: '成功',
               message: '这是一条成功的提示消息',
               type: 'success'
             });
           }, "1000");
         } else {
           return false;
         }
       });
     }
   }
 };
