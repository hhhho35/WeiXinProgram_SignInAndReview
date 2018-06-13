//index.js
//获取应用实例
const app = getApp()

Page({
  data: {
    phone: '',
    password: ''
  },

  // 获取输入账号  
  phoneInput: function (e) {
    this.setData({
      phone: e.detail.value
    })
  },

  // 获取输入密码  
  passwordInput: function (e) {
    this.setData({
      password: e.detail.value
    })
  },

  // 登录  
  login: function () {
    const pagethis = this;
    if (this.data.phone.length == 0 || this.data.password.length == 0) {
      wx.showToast({
        title: '用户名和密码不能为空',
        icon: 'loading',
        duration: 2000
      })
    } else {

      wx.request({
        url: 'http://localhost:8080/WebsiteMode/Test',
        data: {
          username:pagethis.data.phone,
          password:pagethis.data.password
        },
        method: 'POST',
        header: {
          'content-type': 'application/x-www-form-urlencoded' // post方法专门设置
        },
        success: function (res) {
          if(res.data.success == "1"){//标准写法，jsp中只有一个json对象时可以略去data，这里不行 登录成功
          //管理员登录
          if(pagethis.data.phone == "hhhho"){
            wx.showToast({
              title: '管理员您好',
              icon: 'success',
              duration: 2000
            })

            wx.navigateTo({
              url: "../admin/admin",
            })
          }

          //普通用户登录
          else{
            wx.showToast({
              title: '登录成功',
              icon: 'success',
              duration: 2000
            })

            var name = pagethis.data.phone;
            var urlstr = '../days/days?username=' + name;
            wx.navigateTo({
              url: urlstr,
            })
          }
          }
          //登录失败
          else{
            wx.showToast({
              title: '账号或密码错误',
              icon: 'loading',
              duration: 2000
            })
          }
        },
        fail: function (res) {
          console.log(".....fail.....");
        },
        dataType:"json"
      })
    }
  }
}) 