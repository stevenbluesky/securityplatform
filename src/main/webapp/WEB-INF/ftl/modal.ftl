<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width:95%;height:90%;">
        <div class="modal-content">
        <#--引入详情界面-->
            <div class="modal-body">
                <div id="modalid" class="col-sm-12 col-md-12" style="height:540px;width:100%">
                    <iframe id="iframeDetail" class="embed-responsive-item" frameborder="0" src=""
                            style="height:100%;width:100%;"></iframe>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal"><@spring.message code="label.close"/></button>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function() {
        var obj = document.getElementById("modalid");
        obj.style.cssText="height:"+0.8*($(window).height())+"px;overflow: hidden;z-index: 999;";
    })

</script>