<!-- 录入电话卡信息页面 -->
<#include "/_head0.ftl"/>
    <div class="row-horizontal">
        <div class="col-md-1"></div>
        <div class="col-md-10">
            <form class="form-horizontal" id="defaultForm" method="POST">
                <div class="text-center"><h1><@spring.message code='label.userInfo'/></h1></div>
                <hr>
                <div class="form-group">
                    <label for="firstname" class="col-sm-2 control-label" ><@spring.message code='label.firstname'/>
                        *</label>
                    <div class="col-sm-10">
                    ${(userVO.firstname)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="lastname" class="col-sm-2 control-label" ><@spring.message code='label.lastname'/>
                        *</label>
                    <div class="col-sm-10">
                    ${(userVO.lastname)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="ssn" class="col-sm-2 control-label"><@spring.message code='label.ssn'/>*</label>
                    <div class="col-sm-10">
                    ${(userVO.ssn)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="gender" class="col-sm-2 control-label"><@spring.message code='label.gender'/></label>
                    <div class="col-sm-10">
                        <span id="gender"></span>
                    </div>
                </div>

                <div class="form-group">
                    <label for="phonenumber"
                           class="col-sm-2 control-label"><@spring.message code='label.phonenumber'/></label>
                    <div class="col-sm-10">
                    ${(userVO.phonenumber)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="email" class="col-sm-2 control-label"><@spring.message code='label.email'/></label>
                    <div class="col-sm-10">
                    ${(userVO.email)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="fax" class="col-sm-2 control-label"><@spring.message code='label.fax'/></label>
                    <div class="col-sm-10">
                    ${(userVO.fax)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="address" class="col-sm-2 control-label"><@spring.message code='label.address'/>*</label>
                    <div class="col-sm-10">
                        <div class="row text-left">

                            <div class="col-sm-4">
                            ${(userVO.countryname)!}
                            </div>

                            <div class="col-sm-4">
                            ${(userVO.provincename)!}
                            </div>

                            <div class="col-sm-4">
                            ${(userVO.cityname)!}
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="detailaddress"
                           class="col-sm-2 control-label"><@spring.message code='label.detailaddress'/></label>
                    <div class="col-sm-10">
                    ${(userVO.detailaddress)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="codepostfix"
                           class="col-sm-2 control-label"><@spring.message code='label.usercodepostfix'/></label>
                    <div class="col-sm-10">
                    ${(userVO.codepostfix)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="deviceid" class="col-sm-2 control-label"><@spring.message code='label.gatewayID'/>
                        *</label>
                    <div class="col-sm-10">
                    ${(userVO.deviceid)!}
                    </div>
                </div>

                <div class="form-group">
                    <label for="serialnumber" class="col-sm-2 control-label"><@spring.message code='label.phonecardid'/>
                        *</label>
                    <div class="col-sm-10">
                    ${(userVO.serialnumber)!}
                    </div>
                </div>

            </form>

        </div>

        <div class="col-md-1"></div>
    </div>

<!-- JavaScript 部分 -->
    <script type="text/javascript">
        function formatterGender(){
            let gender = ${(userVO.gender)!"-1"};
            let formatterStatus = formatter_gender(gender);
            $("#gender").text(formatterStatus);
        }

        $(function () {
            formatterGender();
        });
    </script>
<#include "/_foot0.ftl"/>