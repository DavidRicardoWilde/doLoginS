function run_init_i_a_s(){
	$('#div_frame').before('<a href="http://www.sina.com.cn/"><img src="http://www.baidu.com/img/bdlogo.gif" ></img></a>');

}

function get_header_heigth()
{
	return 150;
}


$(function(){
	var h = get_header_heigth();
	function resetSize(){
		var winh = parseInt($(window).height()) - h +'px';
		$('#div_frame').css('height', winh);
		$('#mainframe').css('height', winh);
	}
	resetSize();
	$(window).resize(resetSize);

	run_init_i_a_s();
});
