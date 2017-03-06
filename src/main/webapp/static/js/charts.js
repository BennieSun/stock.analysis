$(document).ready(function(){
	
	/**
	 * String转Array int
	 */
	function strToArrayInt(paramsString){
		var paramsString = paramsString,_params = new Array();
		console.log(paramsString);
		//params = '4.9, 3.2, 6.7, 7.5, 9.9, 1.2, 7.0, 26.6, 14.2, -8.3, 6.6, -4.8';
		paramsString = paramsString.replace("[", "").replace("]", "").split(",");
		for(var i=0; i<paramsString.length;i++){
			_params.push(parseFloat(paramsString[i]))
		}
		return _params;
	}
	
	/**
	 * 报表
	 */
	function chartsToDiv(paramsString,time) {
		var _paramsString = paramsString;
		var _time = time;
	    $('#container').highcharts({
	        title: {
	            text: 'Monthly Average Temperature',
	            x: -2 //center
	        },
	        subtitle: {
	            text: 'Source: WorldClimate.com',
	            x: -2
	        },
	        xAxis: {
	            categories: _time
	        },
	        yAxis: {
	            title: {
	                text: 'Temperature (万)'
	            },
	            plotLines: [{
	                value: 0,
	                width: 1,
	                color: '#808080'
	            }]
	        },
	        tooltip: {
	            valueSuffix: '万'
	        },
	        legend: {
	            layout: 'vertical',
	            align: 'right',
	            verticalAlign: 'middle',
	            borderWidth: 0
	        },
	        series: _paramsString
	    });
	};

	/**
	 * ajax请求数据
	 */
	$("#btnName").click(function(){
         requestAjax();
    });
	
	
	function requestAjax(){
		if(""==$("#stockCode").val()){
			alert("stock Code 为空!");
		}
		
		$.ajax({
            type: "POST",
            url: "stockDetailCharts",
            data: {stockCode:$("#stockCode").val(), limitCount:$("#limitCount").val()},
            //data: {stockCode:600370, limitCount:10},
            dataType: "json",
            success: function(data){
            	alert("123");
            	alert(data);
        	console.log("123&&&&&&"+data);
    		if(!data || data.length == 0){
    			alert("没有这个ID!");
    			return;
    		}
           	 var time = data[0];//时间Array
           	 data.shift();//去掉第一组
           	 chartsToDiv(data,time);
            }
        });
	}
	
	document.getElementById('btnDiv').onkeydown=keyDownSearch;
	
	/**
	 * 回车 event
	 */
	function keyDownSearch(e) {    
        // 兼容FF和IE和Opera    
        var theEvent = e || window.event;    
        var code = theEvent.keyCode || theEvent.which || theEvent.charCode;    
        if (code == 13) {    
        	requestAjax();
            return false;    
        }    
        return true;    
    } 
});
				