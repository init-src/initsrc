/**
 * 作者：李楚汉
 * 作用：权限生成类
 * 邮箱：502863488@qq.com
 * 昵称：大神很烦恼（dshfn)
 */

export default {

  // 权限按钮过滤设置
  powerSet: function(data, that) {
    let obj = {
      tableOper: [],
      headerOper: []
    }
    if (data != null) {
      if (data.length > 0) {
        data.forEach(function(item, index) {
          var btn = {
            id: index,
            label: item.name,
            type: item.color,
            perm: item.perm,
            show: true,
            icon: item.icon,
            plain: true,
            disabled: false,
            method: (index, row) => {
              let list = item.perm.split(":")
              if (list[list.length - 1] == "add") {
                that.handleAdd(item.path)
              } else if (list[list.length - 1] == "edit") {
                that.handleEdit(index, row, item.path)
              } else if (list[list.length - 1] == "detail") {
                that.handleDetail(index, row, item.path)
              } else if (list[list.length - 1] == "del") {
                that.handleDel(index, row)
              } else if (list[list.length - 1] == "dels") {
                that.handleDels()
              } else if (list[list.length - 1] == "import") {
                that.handleImport(index, row, item.path)
              } else if (list[list.length - 1] == "export") {
                that.handleExport(index, row, item.path)
              } else if (list[list.length - 1] == "sync") {
                that.handleSync(index, row, item.path)
              } else if (list[list.length - 1] == "download") {
                that.handleDownload(index, row)
              }
            }
          }
          if (item.resource == 2) {
            obj.tableOper.push(btn);
          } else {
            obj.headerOper.push(btn)
          }
        })
      }
    }
    return obj
  },
};
