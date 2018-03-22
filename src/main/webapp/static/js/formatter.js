function formatter_status(value,row,index){
	if(value==1)
		return "正常";
	if(value==0)
		return "未生效";
	if(value==0)
		return "冻结";
	if(value==9)
		return "删除";
}
function formatter_op(value,row,index){
	return "<button class='btn btn-default btn-xs' id='btn1' onclick='xixi()'>操作</button>";
}