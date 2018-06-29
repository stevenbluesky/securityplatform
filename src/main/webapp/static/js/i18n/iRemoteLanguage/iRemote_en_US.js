var lan = {
    success:"success",//1
    addsuccess:"success",//2
    error0:"error",//-1
    varificationcodeerror:"Verification Number Error",//-2
    orgfreeze:"Your institution or parent is frozen. Please contact your superior.",//-3
    dalose:"database connection disconnected, please wait ",//-4
    loginfaied:"Login information error or the account is frozen.",//-98
    orgnameexist:"The company name already exists",//-11
    loginnameexist:"The loginname already exists",//-12
    notpermission:"No Permission",//-99
    notempty: "Mandatory",//-100
    statusnull:"Status is Mandatory.",//-101
    haduser:"User existed!",//-102
    installerneedscode:"If this is new installer, please input Installer Number!",//-103
    duplicateempcode:"Duplicate Installer Number!",//-104
    loaderror: "Loading Data Error!",//-105
    duplicategateway:"This Gateway has binded Organization!",//-106
    gorpnotnull:"Gateway or SIM Mandatory.",//-107
    duplicategatewayuser:"This Gateway has binded User!",//-108
    addgatewayerror:"Gateway doesn't exsit!",//-109
    duplicatephonecarduser:"Duplicate SIM for User, please input others!.",//-110
    simdontexsit:"SIM doesn't exsit",//-111
    devicenoexist:"Device does not exist",//-112
    apinopermission:"No permission",//-113
    codeorpassworderror:"Remote returned some errors ",//-114//-115
    deviceoffline:"Device offline",//-116
    nopermission:"No permission",//-117
    validateCodeFail:"Duplicate Company Code",//-118
    connectdbfail:"Database connect failed",//-1113
    alrgateway:"The user has already bound a gateway, please unbind it first.",//-119
    alrphonecard:"The user has already bound a phone card. Please unbind it first.",//-120
    alrappaccount:"This app account has been binding, please check whether fill in error",//-121
    wholeaddress:"Please fill in the whole address information ",//-122
    notsup:"Unsupported type of device",//不支持的设备类型
    devicetimeout:"Timeout",//超时
    unknownerror:"Unknown error",//未知错误

    normal: "Normal",
    unvalid: 'Ready',
    suspenced: "Suspected",
    deleted: "Delete",
    alarm0:"Stress warning",//?
    status0:"None",
    keyalarm:"Key Alarm",//当有人试图用钥匙开锁时
    closefail:"Close lock failure",
    indoorunlock:"Indoor unlock alarm",
    dooropen0:"door open",
    doorclose0:"door close",
    pswfail:"5 Consecutive password errors",
    keyerror:"Mechanical key mismatch Alarm",

    doorlock: "Magnetic Door",
    doorlock0: "Door Lock",
    smokesensor:"Smoke sensor",
    gassensor:"Gas sensor",
    leaksensor: "Leaking sensor",
    PyroelectricSensors: "Mobile sensors",
    socket0: "Socket",
    Coloringlamp: "Color lamp",
    DSCSecurity:"DSC Security",
    close: "Close",
    open: "Open",
    error: "Failure",
    takepart:"Be torn down",
    alarm:"Alarm",
    noperson:"No one in the sensor range.",
    hasperson:"There's someone in the sensor range.",
    highpoweralarm:"High-Power alarm",
    dooropen:"The door's open.",
    supplierlist:"Distributor List",
    addsupplier:"Add Distributor",
    installerlist:"Installer List",
    addinstaller:"Add Installer",
    employeelist:"Employee List",
    addemployee:"Add Employee",
    gatewaylist:"Gateway List",
    enteringgatewayinfo:"Input Gateway Information",
    gatewaydetail:"Gateway Detail",
    devicelist:"Device List",
    devicedetail:"Device Detail",
    userlist:"User List",
    enteringuserinfo:"Input User Information",
    phonecardlist:"SIM Card List",
    enteringphonecardinfo:"Input SIM Card Information",
    male:"male",
    female:"female",
    withdraw:"Withdraw",
    fortify:"Fortify",
    channel1:"Channel 1 alarm",
    channel2:"Channel 2 alarm",
    channel3:"Channel 3 alarm",
    channel4:"Channel 4 alarm",
    channel5:"Channel 5 alarm",
    channel6:"Channel 6 alarm",
    channel7:"Channel 7 alarm",
    channel8:"Channel 8 alarm",
    channel9:"Channel 9 alarm",
    channel10:"Channel 10 alarm",
    channel11:"Channel 11 alarm",
    channel12:"Channel 12 alarm",
    channel13:"Channel 13 alarm",
    channel14:"Channel 14 alarm",
    channel15:"Channel 15 alarm",
    channel16:"Channel 16 alarm",
    channel17:"Channel 17 alarm",
    channel18:"Channel 18 alarm",
    channel19:"Channel 19 alarm",
    channel20:"Channel 20 alarm",
    channel21:"Channel 21 alarm",
    channel22:"Channel 22 alarm",
    channel23:"Channel 23 alarm",
    channel24:"Channel 24 alarm",
    channel25:"Channel 25 alarm",
    channel26:"Channel 26 alarm",
    channel27:"Channel 27 alarm",
    channel28:"Channel 28 alarm",
    channel29:"Channel 29 alarm",
    channel30:"Channel 30 alarm",
    channel31:"Channel 31 alarm",
    channel32:"Channel 32 alarm",
    channel33:"Channel 33 alarm",
    channel34:"Channel 34 alarm",
    channel35:"Channel 35 alarm",
    channel36:"Channel 36 alarm",
    channel37:"Channel 37 alarm",
    channel38:"Channel 38 alarm",
    channel39:"Channel 39 alarm",
    channel40:"Channel 40 alarm",
    channel41:"Channel 41 alarm",
    channel42:"Channel 42 alarm",
    channel43:"Channel 43 alarm",
    channel44:"Channel 44 alarm",
    channel45:"Channel 45 alarm",
    channel46:"Channel 46 alarm",
    channel47:"Channel 47 alarm",
    channel48:"Channel 48 alarm",
    channel49:"Channel 49 alarm",
    channel50:"Channel 50 alarm",
    channel51:"Channel 51 alarm",
    channel52:"Channel 52 alarm",
    channel53:"Channel 53 alarm",
    channel54:"Channel 54 alarm",
    channel55:"Channel 55 alarm",
    channel56:"Channel 56 alarm",
    channel57:"Channel 57 alarm",
    channel58:"Channel 57 alarm",
    channel59:"Channel 59 alarm",
    channel60:"Channel 60 alarm",
    channel61:"Channel 61 alarm",
    channel62:"Channel 62 alarm",
    channel63:"Channel 63 alarm",
    channel64:"Channel 64 alarm",
    switch1:"switch",
    switch2:"switch",
    switch3:"switch",
    sirenalarm:"siren alarm",
    valvecontroller:"valve controller",
    curtain:"curtain",
    ac:"AC",
    electricitymeter:"electricity meter",
    sos:"sos",
    watermeter:"water meter",
    doorbell:"doorbell",
    suoxin:"doorlock",
    dimmer:"dimmer",
    accesscontrol:"access control",
    airsensor:"air sensor",
    scenepanel1:"scenepane",
    scenepanel2:"scenepane",
    scenepanel3:"scenepane",
    scenepanel4:"scenepane",
    airconditioning:"air conditioning",
    ventilation:"ventilation",
    hometheater:"home theater",
    passiveswitch1:"passive switch",
    passiveswitch2:"passive switch",
    passiveswitch3:"passive switch",
    passiveswitch4:"passive switch",
    passiveswitch6:"passive switch",
    dry1:"Dry",
    dry2:"Dry",
    ventilationsystem1:"ventilation system",
    backgroundmusic:"background music",
    ventilationsystem2:"ventilation system",
    multiswitch1:"multi switch",
    multiswitch2:"multi switch",
    multiswitch3:"multi switch",
    projector:"projector",
    armdevice:"arm device",
    scenepanel21:"scene panel",
    scenepanel22:"scene panel",
    scenepanel24:"scene panel",
    heating:"heating",
    scenepanel23:"scene panel",
    s811:"The channel of ",
    s812:" : close",
    s822:" : open",
};