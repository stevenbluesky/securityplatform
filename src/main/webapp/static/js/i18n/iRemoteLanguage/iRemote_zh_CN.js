var lan = {
    success:"成功",//1
    addsuccess:"添加成功",//2
    error0:"出错啦~",//-1
    varificationcodeerror:"验证码错误",//-2
    orgfreeze:"您的机构或父机构被冻结，请联系上级机构",//-3
    dalose:"数据库连接断开，请稍等",//-4
    orgnameexist:"公司名称已存在",//-11
    loginnameexist:"登录名已存在",//-12
    loginfaied:"密码错误",//-98
    nameerror:"用户名不存在",//-6
    accountfroze:"账号被冻结",//-7
    notpermission:"无权操作",//-99
    notempty: "必填字段不能为空!",//-100
    statusnull:"status不能为空.",//-101
    haduser:"用户名已存在!",//-102
    installerneedscode:"安装员必须要有员工代码!",//-103
    duplicateempcode:"员工代码不能重复!",//-104
    loaderror: "数据加载失败!",//-105
    duplicategateway:"此网关已经绑定过机构!",//-106
    gorpnotnull:"网关或电话卡不能为空.",//-107
    duplicategatewayuser:"此网关已经绑定了用户",//-108
    addgatewayerror:"网关不存在!",//-109
    duplicatephonecarduser:"此电话卡已经绑定了用户.",//-110
    simdontexsit:"电话卡未录入",//-111
    devicenoexist:"设备不存在",//-112
    apinopermission:"远程操作无权限",//-113
    codeorpassworderror:"远程操作出错",//-114//-115F
    deviceoffline:"设备离线",//-116
    nopermission:"无权限",//-117
    validateCodeFail:"公司代码重复",//-118
    connectdbfail:"连接数据库出错",//-1113
    alrgateway:"该用户已经绑定了一个网关，请先解绑再来修改",//-119
    alrphonecard:"该用户已经绑定了一张3G卡，请先解绑再来修改",//-120
    alrappaccount:"该app账号已被绑定，请检查是否填写错误",//-121
    wholeaddress:"请填选完整地址信息",//-122
    notsup:"不支持的设备类型",//不支持的设备类型 133
    devicetimeout:"超时",//超时 -134
    noresponse:"设备无响应",//设备无响应 -135
    devicebusy:"设备忙",//设备忙 -136
    unknownerror:"未知错误",//未知错误 199
    repeatgateway:"网关重复",
    repeatsim:"SIM卡重复",

    inventory:"待激活",
    deactivated:"冻结",
    normal: "正常",
    unvalid: '未生效',
    suspenced: "冻结",
    deleted: "删除",
    alarm0:"胁迫告警",
    status0:"无",
    keyalarm:"钥匙告警",//当有人试图用钥匙开锁时
    closefail:"关锁失败",
    indoorunlock:"室内开锁告警",
    dooropen0:"开门",
    doorclose0:"关门",
    pswfail:"连续5次密码错误",
    keyerror:"机械钥匙不匹配报警",

    doorlock: "门磁",
    doorlock0: "门锁",
    smokesensor:"烟雾传感器",
    gassensor:"燃气传感器",
    leaksensor: "漏水传感器",
    PyroelectricSensors: "移动感应器",
    socket0: "插座",
    Coloringlamp: "调色灯",
    DSCSecurity:"DSC 安防设备",
    close: "关",
    open: "开",
    error: "故障",
    takepart:"被拆",
    alarm:"报警",
    noperson:"感应范围内没有人",
    hasperson:"感应范围内有人",
    highpoweralarm:"大功率告警",
    dooropen:"门开了",
    supplierlist:"服务商列表",
    addsupplier:"新增服务商",
    installerlist:"安装商列表",
    addinstaller:"新增安装商",
    employeelist:"员工列表",
    addemployee:"新增员工",
    gatewaylist:"网关列表",
    enteringgatewayinfo:"录入网关信息",
    gatewaydetail:"网关详情",
    devicelist:"设备列表",
    devicedetail:"设备详情",
    userlist:"用户列表",
    enteringuserinfo:"录入用户信息",
    phonecardlist:"电话卡列表",
    enteringphonecardinfo:"录入电话卡信息",
    male:"男",
    female:"女",
    withdraw:"撤防",
    fortify:"设防",
    channel1:"第1通道报警",
    channel2:"第2通道报警",
    channel3:"第3通道报警",
    channel4:"第4通道报警",
    channel5:"第5通道报警",
    channel6:"第6通道报警",
    channel7:"第7通道报警",
    channel8:"第8通道报警",
    channel9:"第9通道报警",
    channel10:"第10通道报警",
    channel11:"第11通道报警",
    channel12:"第12通道报警",
    channel13:"第13通道报警",
    channel14:"第14通道报警",
    channel15:"第15通道报警",
    channel16:"第16通道报警",
    channel17:"第17通道报警",
    channel18:"第18通道报警",
    channel19:"第19通道报警",
    channel20:"第20通道报警",
    channel21:"第21通道报警",
    channel22:"第22通道报警",
    channel23:"第23通道报警",
    channel24:"第24通道报警",
    channel25:"第25通道报警",
    channel26:"第26通道报警",
    channel27:"第27通道报警",
    channel28:"第28通道报警",
    channel29:"第29通道报警",
    channel30:"第30通道报警",
    channel31:"第31通道报警",
    channel32:"第32通道报警",
    channel33:"第33通道报警",
    channel34:"第34通道报警",
    channel35:"第35通道报警",
    channel36:"第36通道报警",
    channel37:"第37通道报警",
    channel38:"第38通道报警",
    channel39:"第39通道报警",
    channel40:"第40通道报警",
    channel41:"第41通道报警",
    channel42:"第42通道报警",
    channel43:"第43通道报警",
    channel44:"第44通道报警",
    channel45:"第45通道报警",
    channel46:"第46通道报警",
    channel47:"第47通道报警",
    channel48:"第48通道报警",
    channel49:"第49通道报警",
    channel50:"第50通道报警",
    channel51:"第51通道报警",
    channel52:"第52通道报警",
    channel53:"第53通道报警",
    channel54:"第54通道报警",
    channel55:"第55通道报警",
    channel56:"第56通道报警",
    channel57:"第57通道报警",
    channel58:"第58通道报警",
    channel59:"第59通道报警",
    channel60:"第60通道报警",
    channel61:"第61通道报警",
    channel62:"第62通道报警",
    channel63:"第63通道报警",
    channel64:"第64通道报警",
    switch1:"开关",//7
    switch2:"二通道开关",//8
    switch3:"三通道开关",//9
    sirenalarm:"报警器",//10
    valvecontroller:"机械手臂",//12
    curtain:"窗帘",//13
    ac:"空调控制器",//14
    electricitymeter:"途家电表",//15
    sos:"三键SOS报警器",//16
    watermeter:"水表",//17
    doorbell:"猫眼门铃",//18
    suoxin:"锁芯",//19
    dimmer:"调光灯",//20
    accesscontrol:"门禁",//22
    airsensor:"空气质量监测仪",//23
    scenepanel1:"一键情景面板",//24
    scenepanel2:"二键情景面板",//25
    scenepanel3:"三键情景面板",//26
    scenepanel4:"四键情景面板",//27
    airconditioning:"空调外机",//28
    ventilation:"新风",//29
    hometheater:"家庭影院",//30
    passiveswitch1:"一键无电源开关",//31
    passiveswitch2:"二键无电源开关",//32
    passiveswitch3:"三键无电源开关",//33
    passiveswitch4:"四键无电源开关",//34
    passiveswitch6:"六键无电源情景面板",//35
    dry1:"除湿",//36
    dry2:"除湿外机",//37
    ventilationsystem1:"新风外机",//38
    backgroundmusic:"背景音乐",//39
    ventilationsystem2:"白朗新风",//40
    multiswitch1:"二位开关情景面板",//41
    multiswitch2:"三位开关情景面板",//42
    multiswitch3:"四位开关情景面板",//43
    projector:"投影仪",//44
    armdevice:"设防撤防设备",//45
    scenepanel21:"一键便携情景面板",//48
    scenepanel22:"二键便携情景面板",
    scenepanel24:"四键便携情景面板",
    heating:"暖气控制器",//51
    scenepanel23:"三键便携情景面板",
    s811:"第",
    s812:"通道:关",
    s822:"通道:开",
};