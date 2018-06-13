// pages/admin/admin.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    length:0,
    result:[]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },
 
 //按钮点击函数
onClick: function(){
  const pagethis = this;
  wx.request({
    url: 'http://localhost:8080/WebsiteMode/AdminLogin',
    method: 'POST',
    header: {
      'content-type': 'application/x-www-form-urlencoded' // post方法专门设置
    },
    success: function (res) {
      console.log(".....success.....");
      
      var length1 = res.data.length;
      
      //动态更新绑定的数据
      pagethis.setData({
        result:res.data,
        length: length1
      })
    },
    fail: function (res) {
      console.log(".....fail.....");
    },
    dataType: "json"
  })
},

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
  
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
  
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
  }
})