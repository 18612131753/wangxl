/**
 * divId ： 图标生成时所需div的ID
 * title ： 图标的标题
 * XDepict ： X坐标的描述
 * YDepict ： Y坐标的描述
 * jsonDataSource : 数据源，格式：JSON数组 [{XData:xxx,YData:xxx},{XData:xxx,YData:xxx}]
 * @param {Object} divId
 * @param {Object} title
 * @param {Object} XDepict
 * @param {Object} YDepict
 * @param {Object} jsonDataSource
 */
function AmCharts_createColumnChart(divId,title,XDepict,YDepict,jsonDataSource){
	
            
                chart = new AmCharts.AmSerialChart();
                //数据源
                chart.dataProvider = jsonDataSource;
                //横坐标的取值
                chart.categoryField = "XData";
                //纵向    
                chart.rotate = false;
                //加标题
                chart.addTitle(title, 16);
                //3D效果
                chart.depth3D = 20;
                chart.angle = 30;
                chart.startDuration = 1;
                //横坐标
                var categoryAxis = chart.categoryAxis;
                //网格的位置  middle start 默认是 middle
                categoryAxis.gridPosition = "start";
                //横坐标线的颜色
                categoryAxis.axisColor = "#DADADA";
                //网格有颜色部分的透明度
                categoryAxis.fillAlpha = 1;
                //网格的边线显示的颜色深度
                categoryAxis.gridAlpha = 0;
                categoryAxis.title=XDepict;
                //网格的颜色
                categoryAxis.fillColor = "#FAFAFA";
                // value
                var valueAxis = new AmCharts.ValueAxis();
                valueAxis.axisColor = "#DADADA";
                valueAxis.title = YDepict;
                valueAxis.gridAlpha = 0.1;
                chart.addValueAxis(valueAxis);

                // GRAPH
                var graph = new AmCharts.AmGraph();
                graph.valueField = "YData";
                graph.type = "column";
                //提示信息
                graph.balloonText = "[[category]]  :  [[value]]";
                //柱子的边框线 显示的 程度  0--1
                graph.lineAlpha = 0;
                //这里如果用red这种写法的话  只有柱子前面的一面会呈现设定的颜色
                graph.fillColors = "#bf1c25";
                //柱子显示的透明度0---1
                graph.fillAlphas = 1;
                chart.addGraph(graph);

                // WRITE
                chart.write(divId);
    
}
/**
 * divId ： 图标生成时所需div的ID
 * title ： 图标的标题
 * jsonDataSource : 数据源，格式：JSON数组 [{XData:xxx,YData:xxx},{XData:xxx,YData:xxx}]
 * @param {Object} divId
 * @param {Object} title
 * @param {Object} jsonDataSource
 */
function AmCharts_createPieChart(divId,title,jsonDataSource ){
	
           
                // PIE CHART
                var chart = new AmCharts.AmPieChart();
                chart.addTitle(title, 16);
                chart.dataProvider = jsonDataSource;
                chart.titleField = "XData";
                chart.valueField = "YData";
                //各个饼之间的分割线的颜色
                chart.outlineColor = "#FFFFFF";
                //各个饼之间分割线的透明度
                chart.outlineAlpha = 0.8;
                //各个饼之间分割线的宽度
                chart.outlineThickness = 2;
                //3D效果
                chart.depth3D = 15;
                chart.angle = 30;
                // WRITE
                chart.write(divId);
         
}
/**
 * divId ： 图标生成时所需div的ID
 * title ： 图标的标题
 * XDepict ： X坐标的描述
 * YDepict ： Y坐标的描述
 * jsonDataSource : 数据源，格式：JSON数组 [{XData:xxx,YData:xxx},{XData:xxx,YData:xxx}]
 * @param {Object} divId
 * @param {Object} title
 * @param {Object} XDepict
 * @param {Object} YDepict
 * @param {Object} jsonDataSource
 */
function AmCharts_createLineChart(divId,title,XDepict,YDepict,jsonDataSource){
		
			var chart = new AmCharts.AmSerialChart();
			chart.pathToImages = "script/images/amcharts/";
            chart.dataProvider = jsonDataSource;
            chart.categoryField = "XData";
            chart.addTitle(title, 16);
            var categoryAxis = chart.categoryAxis;
            categoryAxis.gridAlpha = 0;
            categoryAxis.title=XDepict;
            
            var valueAxis = new AmCharts.ValueAxis();
            valueAxis.gridAlpha = 0.1;
            valueAxis.title=YDepict;
            chart.addValueAxis(valueAxis);
            
            
            // GRAPH
            var graph = new AmCharts.AmGraph();
            graph.lineColor = "#7717D7";
            graph.valueField = "YData";
            graph.bullet = "round";
            graph.hideBulletsCount = 50;
            chart.addGraph(graph);
            
             // CURSOR
            var chartCursor = new AmCharts.ChartCursor();
            chartCursor.cursorAlpha = 0;
            chart.addChartCursor(chartCursor);
            //Scrollbar
            var chartScrollbar = new AmCharts.ChartScrollbar();
            chart.addChartScrollbar(chartScrollbar);
            chart.write(divId);
	
}