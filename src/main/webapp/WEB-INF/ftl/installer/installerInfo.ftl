<!-- 新增服务商页面 -->
<#include "../_head0.ftl"/>
<style type="text/css">
</style>
    <div class="row-horizontal">
        <#--<div class="col-md-1"></div>-->
        <div class="col-md-12">
            <form class="form-horizontal" id="defaultForm">
                <div class="text-center"><h1><@spring.message code="label.installerInfo"/></h1></div>
                <hr>
                <div class="text-left"><h4><@spring.message code="label.installerInfo"/></h4></div>
                <hr>

                <div class="form-group">
                    <label for="name" class="col-sm-3"
                           style="text-align: left;"><@spring.message code="label.parentsupplier"/></label>
                    <div class="col-sm-9">
                    ${(orgVO.parentorgname)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="code" class="col-sm-3"
                           style="text-align: left;"><@spring.message code="label.code"/></label>
                    <div class="col-sm-9">
                    ${(orgVO.parentorgcode)!}
                    </div>
                </div>
                <hr>
                <div class="form-group">
                    <label for="name" class="col-sm-3"
                           style="text-align: left;"><@spring.message code="label.orgname"/></label>
                    <div class="col-sm-9">
                    ${(orgVO.name)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="code" class="col-sm-3"
                           style="text-align: left;"><@spring.message code="label.groupid"/></label>
                    <div class="col-sm-9">
                    ${(orgVO.code)!}
                    </div>
                </div>
                <div class="form-group">
                    <label for="detailaddress" class="col-sm-3 "
                           style="text-align: left;"><@spring.message code="label.detailaddress"/></label>
                    <div class="col-sm-9">
                    ${(orgVO.address.detailaddress)!}
                    </div>
                </div>
                <div class="form-group">
                    <label for="country" class="col-sm-3 "
                           style="text-align: left;"></label>
                    <div class="col-sm-9">
                            ${(orgVO.address.country)!}&nbsp;${(orgVO.address.province)!}&nbsp;${(orgVO.address.city)!}
                    </div>
                </div>



                <div class="form-group">
                    <label for="postal" class="col-sm-3 "
                           style="text-align: left;"><@spring.message code="label.orgpostal"/></label>
                    <div class="col-sm-9">
                    ${(orgVO.address.postal)!}
                    </div>
                </div>
                <div class="form-group">
                    <label for="bdetailaddress" class="col-sm-3 "
                           style="text-align: left;"><@spring.message code="label.bdetailaddress"/></label>
                    <div class="col-sm-9">
                    ${(orgVO.baddress.detailaddress)!}
                    </div>
                </div>
                <div class="form-group">
                    <label for="bcountry" class="col-sm-3 "
                           style="text-align: left;"></label>
                    <div class="col-sm-9">
                            ${(orgVO.baddress.country)!}&nbsp;${(orgVO.baddress.province)!}&nbsp;${(orgVO.baddress.city)!}
                    </div>
                </div>



                <div class="form-group">
                    <label for="bpostal" class="col-sm-3 "
                           style="text-align: left;"><@spring.message code="label.borgpostal"/></label>
                    <div class="col-sm-9">
                    ${(orgVO.baddress.postal)!}
                    </div>
                </div>
<#--                <hr>
                <div class="text-left"><h5><@spring.message code="label.admin"/></h5></div>
                <div class="form-group">
                    <label for="loginname" class="col-sm-3 "
                           style="text-align: left;"><@spring.message code="label.loginname"/>
                    </label>
                    <div class="col-sm-9">
                    ${(orgVO.loginname)!}
                    </div>
                </div>-->
                <div style="visibility: hidden;">br</div>
                <div class="text-left"><h4><@spring.message code="label.installerperson"/></h4></div>
                <hr>
                <div class="form-group">
                    <label for="sname" class="col-sm-3 "
                           style="text-align: left;"><@spring.message code="label.name"/></label>
                    <div class="col-sm-9">
                    ${(orgVO.sname)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="sphonenumber"
                           class="col-sm-3 "
                           style="text-align: left;"><@spring.message code="label.phonenumber"/></label>
                    <div class="col-sm-9">
                    ${(orgVO.sphonenumber)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="sfax" class="col-sm-3 "
                           style="text-align: left;"><@spring.message code="label.fax"/></label>
                    <div class="col-sm-9">
                    ${(orgVO.sfax)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="semail" class="col-sm-3 "
                           style="text-align: left;"><@spring.message code="label.email"/></label>
                    <div class="col-sm-9">
                    ${(orgVO.semail)!}
                    </div>
                </div>

                <div style="visibility: hidden;">br</div>
                <div class="text-left"><h4><@spring.message code="label.sparentrorg"/></h4></div>
                <hr>
                <div class="form-group">
                    <label for="csname"
                           class="col-sm-3 "
                           style="text-align: left;"><@spring.message code="label.parentrorgname"/></label>
                    <div class="col-sm-9">
                    ${(orgVO.csname)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="cscountry"
                           class="col-sm-3 "
                           style="text-align: left;"><@spring.message code="label.parentrorgaddress"/></label>
                    <div class="col-sm-9">
                            ${(orgVO.csaddress.country)!}&nbsp;${(orgVO.csaddress.province)!}&nbsp;${(orgVO.csaddress.city)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="cspostal"
                           class="col-sm-3 "
                           style="text-align: left;"><@spring.message code="label.parentorgpostal"/></label>
                    <div class="col-sm-9">
                    ${(orgVO.csaddress.postal)!}
                    </div>
                </div>

                <div style="visibility: hidden;">br</div>
                <div class="text-left"><h4><@spring.message code="label.parentinstallerperson"/></h4></div>
                <hr>
                <div class="form-group">
                    <label for="cspname" class="col-sm-3 "
                           style="text-align: left;"><@spring.message code="label.name"/></label>
                    <div class="col-sm-9">
                    ${(orgVO.cspname)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="cspphonenumber"
                           class="col-sm-3 "
                           style="text-align: left;"><@spring.message code="label.phonenumber"/></label>
                    <div class="col-sm-9">
                    ${(orgVO.cspphonenumber)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="cspfax" class="col-sm-3 "
                           style="text-align: left;"><@spring.message code="label.fax"/></label>
                    <div class="col-sm-9">
                    ${(orgVO.cspfax)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="cspemail" class="col-sm-3 "
                           style="text-align: left;"><@spring.message code="label.email"/></label>
                    <div class="col-sm-9">
                    ${(orgVO.cspemail)!}
                    </div>
                </div>

                <div style="visibility: hidden;">br</div>

            </form>
        </div>

        <#--<div class="col-md-1"></div>-->
    </div>

<!-- JavaScript 部分 -->
	<script src="../static/js/addressController.js"></script>
<#--<script src="../static/js/addressController2.js"></script>-->
    <script src="../static/js/validAddress.js"></script>

<script type="text/javascript">
</script>

<#include "../_foot0.ftl"/>