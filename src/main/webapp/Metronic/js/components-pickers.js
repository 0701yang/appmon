var ComponentsPickers = function () {

    var handleDateRangePickers = function () {
        if (!jQuery().daterangepicker) {
            return;
        }

        $('#reportrange').daterangepicker({
                opens: (Metronic.isRTL() ? 'left' : 'right'),
                //startDate: moment().subtract('days', 29),
                //endDate: moment(),
                minDate: 0,
                maxDate: 0,
                dateLimit: {
                    days: 60
                },
                showDropdowns: true,
                showWeekNumbers: true,
                timePicker: true,
                timePickerIncrement: 1,
                timePicker12Hour: false,
                ranges: {
                    '今天': [moment(), moment()],
                    '昨天': [moment().subtract( 1,'days'), moment().subtract(1,'days')],
                    '前7天': [moment().subtract( 6,'days'), moment()],
                    '后30天': [moment().subtract( 29,'days'), moment()],
                    '最后一个月': [moment().startOf('month'), moment().endOf('month')],
                    '上个月': [moment().subtract(1,'month').startOf('month'), moment().subtract( 1,'month').endOf('month')]
                },
                buttonClasses: ['btn'],
                applyClass: 'green',
                cancelClass: 'default',
                format: 'YYYY-MM-DD HH:mm:ss',
                separator: ' to ',
                locale: {
                    applyLabel: '确定',
                    cancelLabel : '取消',
                    fromLabel: '起始时间',
                    toLabel: '结束时间',
                    customRangeLabel: '自定义',
                    daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
                    monthNames: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
                    firstDay: 1
                }
            },
            function (start, end) {
                $('#reportrange span').html(start.format('YYYY-MM-DD HH:mm:ss') + ' -- ' + end.format('YYYY-MM-DD HH:mm:ss'));
            }
        );
        //Set the initial state of the picker label
        $('#reportrange span').html(moment().format('YYYY-MM-DD HH:mm:ss') + ' -- ' + moment().format('YYYY-MM-DD HH:mm:ss'));
    }

    return {
        //main function to initiate the module
        init: function () {
            handleDateRangePickers();
            
        }
    };

}();