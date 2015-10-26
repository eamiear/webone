$(function() {
	App.init();
	
	//设置值
	App.setTotalCount(3235);
	App.upldatePercentValue(45);
	App.setNormalValue(413);
});	


App = (function($){
	
	/**
	 * 初始化
	 */
	var _init = function(){
		_initEasyChart();
		chart = window.chart = $('.chart').data('easyPieChart');
	}
	
	/**
	 * 初始化easyChart图表
	 */
	var _initEasyChart = function(){
		$('.chart').easyPieChart({
			easing : 'easeOutBounce',
			delay: 3000,
			barColor: function(percent) {
					    var ctx = this.renderer.getCtx();
					    var canvas = this.renderer.getCanvas();
					    var gradient = ctx.createLinearGradient(0,0,canvas.width,0);
					        gradient.addColorStop(0, "rgba(115,243,81, 1)");
					        gradient.addColorStop(1, "rgba(115,243,81, 0.8)");
					    return gradient;
					  },
			trackColor: '#006B87',
			scaleColor: false,
			scaleLength: 15,
			lineWidth: 15,
			trackWidth: 15,
			lineCap: 'round',
			rotate : 200,
			size: 188,
			onStep : function(from, to, percent) {
				$(this.el).find('.percent').text(Math.round(percent));
			}
		});
	}
	
	/**
	 * 更新Chart图表百分值
	 * @param percent  百分比值
	 */
	var _upldatePercentValue = function(percent){
		chart.update(percent);
	}
	
	/**
	 * 设置图表总数值
	 * @param totalValue
	 */
	var _setTotalCount = function(totalValue){
		$("#total").text(totalValue);
	}
	
	/**
	 * 设置正常值
	 * @param normalValue
	 */
	var _setNormalValue = function(normalValue){
		$("#normal").text(normalValue);
	}
	
	/**
	 * 设置警告值
	 * @param warnValue
	 */
	var _setWarnValue = function(warnValue){
		$("#warn").text(warnValue);
	}
	
	/**
	 * 设置异常值
	 * @param abnormalValue
	 */
	var _setAbnormalValue = function(abnormalValue){
		$("#abnormal").text(abnormalValue);
	}
	
	/**
	 * 设置检测异常值
	 * @param failureValue
	 */
	var _setDetectionFailure = function(failureValue){
		$("#detectionFailure").text(failureValue);
	}
	
	/**
	 * 设置偏暗值
	 * @param darkValue
	 */
	var _setPartialDarkValue = function(darkValue){
		$("#dark").text(darkValue);
	}
	
	/**
	 * 设置偏亮值
	 * @param lightValue
	 */
	var _setPartialLightValue = function(lightValue){
		$("#partialLight").text(lightValue);
	}
	
	/**
	 * 设置偏色值
	 * @param colorValue
	 */
	var _setPartialColorValue = function(colorValue){
		$("#partialColor").text(colorValue);
	}
	
	/**
	 * 设置雪花值
	 * @param snowValue
	 */
	var _setSnowValue = function(snowValue){
		$("#snow").text(snowValue);
	}
	
	/**
	 * 设置模糊值
	 * @param fuzzyValue
	 */
	var _setFuzzyValue = function(fuzzyValue){
		$("#fuzzy").text(fuzzyValue);
	}
	
	/**
	 * 设置清晰度值
	 * @param value
	 */
	var _setDefinitionValue = function(value){
		$("#definition").text(value);
	}
	
	/**
	 * 设置条纹值
	 * @param value
	 */
	var _setFringeValue = function(value){
		$("#fringe").text(value);
	}
	
	/**
	 * 设置冻结值
	 * @param value
	 */
	var _setFrozenValue = function(value){
		$("#forzen").text(value);
	}
	
	/**
	 * 设置抖动值
	 * @param value
	 */
	var _setShakeValue = function(value){
		$("#shake").text(value);
	}
	
	/**
	 * 设置遮挡值
	 * @param value
	 */
	var _setOcclusionValue = function(value){
		$("#occlusion").text(value);
	}
	
	/**
	 * 设置信号丢失值
	 * @param value
	 */
	var _setSignalLossValue = function(value){
		$("#signalLoss").text(value);
	}
	
	return {
		init : _init,
		upldatePercentValue : _upldatePercentValue,
		setTotalCount : _setTotalCount,
		setNormalValue : _setNormalValue,
		setWarnValue : _setWarnValue,
		setAbnormalValue : _setAbnormalValue,
		setDetectionFailure : _setDetectionFailure,
		setPartialDarkValue : _setPartialDarkValue,
		setPartialLightValue : _setPartialLightValue,
		setPartialColorValue : _setPartialColorValue,
		setSnowValue : _setSnowValue,
		setFuzzyValue : _setFuzzyValue,
		setDefinitionValue : _setDefinitionValue,
		setFringeValue : _setFringeValue,
		setFrozenValue : _setFrozenValue,
		setShakeValue : _setShakeValue,
		setOcclusionValue : _setOcclusionValue,
		setSignalLossValue : _setSignalLossValue
	}
})(jQuery);