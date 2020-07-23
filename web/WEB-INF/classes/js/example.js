var option;

var loading;
function openLoading() {
    loading = layer.msg('加载中', {
        time: 0,
        icon: 16,
        shade: [0.3, '#000']
    });
}
function closeLoading() {
    layer.close(loading);
}

function NewDate(str) {
    var day = str.split(' ');
    str = day[0].split('-');
    var date = new Date();
    date.setUTCFullYear(str[0], str[1] - 1, str[2]);
    date.setUTCHours(0, 0, 0, 0);
    return date;
}

var Objects = {
    format: function () {
        var obj;
        var params = [];
        for (var i = 0; i < arguments.length; i++) {
            if (0 == i) {
                obj = arguments[i];
            } else {
                params[i - 1] = arguments[i];
            }
        }
        var reg = /{(\d+)}/gm;
        return obj.replace(reg, function (match, name) {
            return params[~~name];
        });
    },
    floatPlus: function (params) {
        var total = 0;
        for (var i = 0; i < params.length; i++) {
            total = total.toFixed(2);
            total = parseFloat(total);
            var score = parseFloat(params[i]);
            score = score.toFixed(2);
            score = parseFloat(score);
            total += score;
        }
        total = total.toFixed(2);
        total = parseFloat(total);
        return total;
    }
};

var Ajax = {
    jsonGet: function (url, callback) {
        $.get(url, callback);
    },
    jsonPost: function (url, postData, callback) {
        $.ajax({
            url: url,
            type: 'POST',
            data: JSON.stringify(postData),
            dataType: 'json',
            contentType: 'application/json',
            success: callback
        });
    },
    jsonPostAsync: function (url, postData, callback) {
        $.ajax({
            url: url,
            type: 'POST',
            async: false,
            data: JSON.stringify(postData),
            dataType: 'json',
            contentType: 'application/json',
            success: callback
        });
    }
};

function dateTimeFormat(timestamp) {
    return (new Date(timestamp)).format("yyyy-MM-dd hh:mm:ss");
}
function dateFormat(timestamp) {
    var newDate = new Date();
     newDate.setTime(timestamp);
    return newDate.toLocaleDateString();
}
/**
 * 日期比较 yyyy-mm-dd 当天有效
 * @param start
 * @param end
 * @returns {boolean}
 */
function dateCompareAddDay(start, end) {
    var arr = start.split("-");
    var starttime = new Date(arr[0], arr[1], arr[2]);
    var starttimes = starttime.getTime();

    var arrs = end.split("-");
    var lktime = new Date(arrs[0], arrs[1], arrs[2]);
    var lktimes = lktime.getTime();

    if (starttimes > lktimes) {
        return false;
    } else {
        return true;
    }
}

/**
 * 日期比较 舍去时分秒
 * @param start
 * @param end
 * @returns {boolean}
 */
function dateCompare(start, end) {
    var arr = start.split("-");
    //转换日期不准确
    // var starttime = new Date(arr[0], arr[1], arr[2]);

    var starttimes = new Date(start.replace(/-/g,"/")).getTime();

    var lktimes = new Date(end.replace(/-/g,"/")).getTime();

    //转换日期不准确
    // lktime = new Date(lktime.getFullYear(),lktime.getMonth()+1,lktime.getDate());

    if (starttimes >= lktimes) {
        return false;
    } else {
        return true;
    }
}


/**
 * 日期比较 yyyy-mm-dd HH:mm:ss
 * @param start
 * @param end
 * @returns {boolean}
 */
function compareTime(startTime,endTime) {
//          var start_time=startTime.replace(/-|\s|:|\//g,'').replace(' ', ''); //用这个加强版也可以
    var start_time = startTime.replace(/-|\s|:|\//g, '');
    var end_time = endTime.replace(/-|\s|:|\//g, '');
    if (start_time > end_time) {
        return false;
    }
    else {
        return true;
    }
}

function boolFormat(b) {
    return b ? "是" : "否";
}