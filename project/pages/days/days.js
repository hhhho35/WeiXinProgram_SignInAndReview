//获取应用实例
var app = getApp();
var calendarSignData;
var date;
var calendarSignDay;
var QQMapWX = require('/qqmap-wx-jssdk.js');
var qqmapsdk;
var username;

Page({
  //事件处理函数
  Sign: function () {
    calendarSignData[date] = date;
    //console.log(calendarSignData);
    calendarSignDay = calendarSignDay + 1;
    wx.setStorageSync("calendarSignData", calendarSignData);
    wx.setStorageSync("calendarSignDay", calendarSignDay);

    wx.showToast({
      title: '签到成功',
      icon: 'success',
      duration: 2000
    })
    var that = this
    var addr = " "
    qqmapsdk = new QQMapWX({
      key: 'BQWBZ-6ACAW-GK2RB-RPIJW-IFYJO-3RFE2'
    });
    wx.getLocation({
      type: 'wgs84',
      success: function (res) {
        console.log(res)
        //console.log(app.globalData.userid)
        qqmapsdk.reverseGeocoder({
          location: {
            latitude: res.latitude,
            longitude: res.longitude
          },
          success: function (res) {
            addr = res.result.address;
            console.log(addr);
            //console.log(app.globalData.userid);
            //console.log(app.globalData.userName);
            wx.request({
              url: 'http://localhost:8080/WebsiteMode/Login',
              data: {
                address: addr,
                Name: username
              },
              method: 'GET',
              header: {
                'content-type': 'application/json' // 默认值
              }
            })
          }
        })
      }
    })
    
    this.setData({
      calendarSignData: calendarSignData,
      calendarSignDay: calendarSignDay
    })
  },
  onLoad: function (res) {
    username = res.username;
    var mydate = new Date();
    var year = mydate.getFullYear();
    var month = mydate.getMonth() + 1;
    date = mydate.getDate();
    console.log("date" + date)
    var day = mydate.getDay();
    console.log(day)
    var nbsp = 7 - ((date - day) % 7);
    console.log("nbsp" + nbsp);
    var monthDaySize;
    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
      monthDaySize = 31;
    } else if (month == 4 || month == 6 || month == 9 || month == 11) {
      monthDaySize = 30;
    } else if (month == 2) {
      // 计算是否是闰年,如果是二月份则是29天
      if ((year - 2000) % 4 == 0) {
        monthDaySize = 29;
      } else {
        monthDaySize = 28;
      }
    };
    // 判断是否签到过
    if (wx.getStorageSync("calendarSignData") == null || wx.getStorageSync("calendarSignData") == '') {
      wx.setStorageSync("calendarSignData", new Array(monthDaySize));
      
    };
    if (wx.getStorageSync("calendarSignDay") == null || wx.getStorageSync("calendarSignDay") == '') {
      wx.setStorageSync("calendarSignDay", 0);
    }
    calendarSignData = wx.getStorageSync("calendarSignData")
    calendarSignDay = wx.getStorageSync("calendarSignDay")
    console.log(calendarSignData);
    console.log(calendarSignDay)
    this.setData({
      year: year,
      month: month,
      nbsp: nbsp,
      monthDaySize: monthDaySize,
      date: date,
      calendarSignData: calendarSignData,
      calendarSignDay: calendarSignDay
    })
  }
})