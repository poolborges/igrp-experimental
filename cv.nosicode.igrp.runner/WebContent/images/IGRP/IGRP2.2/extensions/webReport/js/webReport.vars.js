var WEBREPORT = {
  textEditor:'p_report_editor',
  toolsBar:'.bWebReport',
  classDrag:'.drag',
  tmpl:'<hr class="page"><div size="A4">'+
    '<div id="header"><p>-- Cabe&ccedil;alho --&nbsp;</p></div><p class="page">&nbsp;</p>'+
    '<div id="content"><p>-- Corpo --&nbsp;</p></div>'+
    '<div id="footer"></div>'+
    '</div><hr class="page" /><p class="page">&nbsp;</p>',
  tabMenu:{
    id:'tab',
    classItem:'.itemMenu'
  },
  listReport:{
    id:'#listReports',
    add:'.addReports',
    classItem:'.linkReports',
    btn:'.btn',
    edit:'.editReport',
    info:'.infoReport',
    divInfo:'.info',
    linkEdit:'p_edit_name_report',
    showTitle:'#showTitle',
    reportTitle:'.spanTitle'
  },
  dialogReport:{
    id:'#reportTitle',
    ok:'#btrOK',
    cancel:'#btrCancel',
    addTitle:{
      title:dtr,
      inputName:dtr_NTinput,
      inputCodName:dtr_NCinput,
      message:'<div class="igrp_item"><div class="col-1-4"><label>'+dtr_LTinput+'</label></div><div class="col-9-12">'+
        '<input type="text" name="'+dtr_NTinput+'"/></div><div class="_clear"/></div>'+
        '<div class="igrp_item"><div class="col-1-4"><label>'+dtr_LCinput+'</label>'+
        '</div><div class="col-9-12"><input type="text" name="'+dtr_NCinput+'"/></div><div class="_clear"/></div>'
    },
    listKeys:{
      inputName:'p_key',
      title:dkr,
      arrName:'name_array',
      arrValue:'value_array'
    }
  },
  aplicacao:{
    selectName:'p_env_fk',
    tmplXsl:'/extensions/webReport/xsl/IGRP-reports.tmpl.xsl',
    link:'p_env_frm_url',
  },
  dataSource:{
    selectName:'p_datasorce_app',
    tmplXsl:'/extensions/webReport/xsl/IGRP-datasorce.tmpl.xsl',
    link:'p_link_source',
    id:'#listElement',
    add:'addDataSource',
    edit:'#editDataSource',
    options:'.tree-menu-options',
    macthXmlDataSource:'list datasorce_app',
    macthXmlLinkAddDataSource:'value link_add_source'
  },
  save:{
    fileXsl:'p_xslreport',
    fileText:'p_textreport',
    idReport:'p_id',
    title:'p_title'
  },
  listType:['table','ul','ol'],
  envelopeXsl:{
    initEnvelope:'<!DOCTYPE xsl:stylesheet  ['+
      '<!ENTITY nbsp   "&#160;" >'+
      '<!ENTITY iexcl  "&#161;" >'+
      '<!ENTITY cent   "&#162;" >'+
      '<!ENTITY pound  "&#163;" >'+
      '<!ENTITY curren "&#164;" >'+
      '<!ENTITY yen    "&#165;" >'+
      '<!ENTITY brvbar "&#166;" >'+
      '<!ENTITY sect   "&#167;" >'+
      '<!ENTITY uml    "&#168;" >'+
      '<!ENTITY copy   "&#169;" >'+
      '<!ENTITY ordf   "&#170;" >'+
      '<!ENTITY laquo  "&#171;" >'+
      '<!ENTITY not    "&#172;" >'+
      '<!ENTITY shy    "&#173;" >'+
      '<!ENTITY reg    "&#174;" >'+
      '<!ENTITY macr   "&#175;" >'+
      '<!ENTITY deg    "&#176;" >'+
      '<!ENTITY plusmn "&#177;" >'+
      '<!ENTITY sup2   "&#178;" >'+
      '<!ENTITY sup3   "&#179;" >'+
      '<!ENTITY acute  "&#180;" >'+
      '<!ENTITY micro  "&#181;" >'+
      '<!ENTITY para   "&#182;" >'+
      '<!ENTITY middot "&#183;" >'+
      '<!ENTITY cedil  "&#184;" >'+
      '<!ENTITY sup1   "&#185;" >'+
      '<!ENTITY ordm   "&#186;" >'+
      '<!ENTITY raquo  "&#187;" >'+
      '<!ENTITY frac14 "&#188;" >'+
      '<!ENTITY frac12 "&#189;" >'+
      '<!ENTITY frac34 "&#190;" >'+
      '<!ENTITY iquest "&#191;" >'+
      '<!ENTITY Agrave "&#192;" >'+
      '<!ENTITY Aacute "&#193;" >'+
      '<!ENTITY Acirc  "&#194;" >'+
      '<!ENTITY Atilde "&#195;" >'+
      '<!ENTITY Auml   "&#196;" >'+
      '<!ENTITY Aring  "&#197;" >'+
      '<!ENTITY AElig  "&#198;" >'+
      '<!ENTITY Ccedil "&#199;" >'+
      '<!ENTITY Egrave "&#200;" >'+
      '<!ENTITY Eacute "&#201;" >'+
      '<!ENTITY Ecirc  "&#202;" >'+
      '<!ENTITY Euml   "&#203;" >'+
      '<!ENTITY Igrave "&#204;" >'+
      '<!ENTITY Iacute "&#205;" >'+
      '<!ENTITY Icirc  "&#206;" >'+
      '<!ENTITY Iuml   "&#207;" >'+
      '<!ENTITY ETH    "&#208;" >'+
      '<!ENTITY Ntilde "&#209;" >'+
      '<!ENTITY Ograve "&#210;" >'+
      '<!ENTITY Oacute "&#211;" >'+
      '<!ENTITY Ocirc  "&#212;" >'+
      '<!ENTITY Otilde "&#213;" >'+
      '<!ENTITY Ouml   "&#214;" >'+
      '<!ENTITY times  "&#215;" >'+
      '<!ENTITY Oslash "&#216;" >'+
      '<!ENTITY Ugrave "&#217;" >'+
      '<!ENTITY Uacute "&#218;" >'+
      '<!ENTITY Ucirc  "&#219;" >'+
      '<!ENTITY Uuml   "&#220;" >'+
      '<!ENTITY Yacute "&#221;" >'+
      '<!ENTITY THORN  "&#222;" >'+
      '<!ENTITY szlig  "&#223;" >'+
      '<!ENTITY agrave "&#224;" >'+
      '<!ENTITY aacute "&#225;" >'+
      '<!ENTITY acirc  "&#226;" >'+
      '<!ENTITY atilde "&#227;" >'+
      '<!ENTITY auml   "&#228;" >'+
      '<!ENTITY aring  "&#229;" >'+
      '<!ENTITY aelig  "&#230;" >'+
      '<!ENTITY ccedil "&#231;" >'+
      '<!ENTITY egrave "&#232;" >'+
      '<!ENTITY eacute "&#233;" >'+
      '<!ENTITY ecirc  "&#234;" >'+
      '<!ENTITY euml   "&#235;" >'+
      '<!ENTITY igrave "&#236;" >'+
      '<!ENTITY iacute "&#237;" >'+
      '<!ENTITY icirc  "&#238;" >'+
      '<!ENTITY iuml   "&#239;" >'+
      '<!ENTITY eth    "&#240;" >'+
      '<!ENTITY ntilde "&#241;" >'+
      '<!ENTITY ograve "&#242;" >'+
      '<!ENTITY oacute "&#243;" >'+
      '<!ENTITY ocirc  "&#244;" >'+
      '<!ENTITY otilde "&#245;" >'+
      '<!ENTITY ouml   "&#246;" >'+
      '<!ENTITY divide "&#247;" >'+
      '<!ENTITY oslash "&#248;" >'+
      '<!ENTITY ugrave "&#249;" >'+
      '<!ENTITY uacute "&#250;" >'+
      '<!ENTITY ucirc  "&#251;" >'+
      '<!ENTITY uuml   "&#252;" >'+
      '<!ENTITY yacute "&#253;" >'+
      '<!ENTITY thorn  "&#254;" >'+
      '<!ENTITY yuml   "&#255;" >'+
      '<!ENTITY lt      "&#38;#60;">'+
      '<!ENTITY gt      "&#62;" >'+
      '<!ENTITY amp     "&#38;#38;">'+
      '<!ENTITY apos    "&#39;" >'+
      '<!ENTITY quot    "&#34;" >'+
      '<!ENTITY OElig   "&#338;" >'+
      '<!ENTITY oelig   "&#339;" >'+
      '<!ENTITY Scaron  "&#352;" >'+
      '<!ENTITY scaron  "&#353;" >'+
      '<!ENTITY Yuml    "&#376;" >'+
      '<!ENTITY circ    "&#710;" >'+
      '<!ENTITY tilde   "&#732;" >'+
      '<!ENTITY ensp    "&#8194;" >'+
      '<!ENTITY emsp    "&#8195;" >'+
      '<!ENTITY thinsp  "&#8201;" >'+
      '<!ENTITY zwnj    "&#8204;" >'+
      '<!ENTITY zwj     "&#8205;" >'+
      '<!ENTITY lrm     "&#8206;" >'+
      '<!ENTITY rlm     "&#8207;" >'+
      '<!ENTITY ndash   "&#8211;" >'+
      '<!ENTITY mdash   "&#8212;" >'+
      '<!ENTITY lsquo   "&#8216;" >'+
      '<!ENTITY rsquo   "&#8217;" >'+
      '<!ENTITY sbquo   "&#8218;" >'+
      '<!ENTITY ldquo   "&#8220;" >'+
      '<!ENTITY rdquo   "&#8221;" >'+
      '<!ENTITY bdquo   "&#8222;" >'+
      '<!ENTITY dagger  "&#8224;" >'+
      '<!ENTITY Dagger  "&#8225;" >'+
      '<!ENTITY permil  "&#8240;" >'+
      '<!ENTITY lsaquo  "&#8249;" >'+
      '<!ENTITY rsaquo  "&#8250;" >'+
      '<!ENTITY euro    "&#8364;" >'+
      '<!ENTITY fnof     "&#402;" >'+
      '<!ENTITY Alpha    "&#913;" >'+
      '<!ENTITY Beta     "&#914;" >'+
      '<!ENTITY Gamma    "&#915;" >'+
      '<!ENTITY Delta    "&#916;" >'+
      '<!ENTITY Epsilon  "&#917;" >'+
      '<!ENTITY Zeta     "&#918;" >'+
      '<!ENTITY Eta      "&#919;" >'+
      '<!ENTITY Theta    "&#920;" >'+
      '<!ENTITY Iota     "&#921;" >'+
      '<!ENTITY Kappa    "&#922;" >'+
      '<!ENTITY Lambda   "&#923;" >'+
      '<!ENTITY Mu       "&#924;" >'+
      '<!ENTITY Nu       "&#925;" >'+
      '<!ENTITY Xi       "&#926;" >'+
      '<!ENTITY Omicron  "&#927;" >'+
      '<!ENTITY Pi       "&#928;" >'+
      '<!ENTITY Rho      "&#929;" >'+
      '<!ENTITY Sigma    "&#931;" >'+
      '<!ENTITY Tau      "&#932;" >'+
      '<!ENTITY Upsilon  "&#933;" >'+
      '<!ENTITY Phi      "&#934;" >'+
      '<!ENTITY Chi      "&#935;" >'+
      '<!ENTITY Psi      "&#936;" >'+
      '<!ENTITY Omega    "&#937;" >'+
      '<!ENTITY alpha    "&#945;" >'+
      '<!ENTITY beta     "&#946;" >'+
      '<!ENTITY gamma    "&#947;" >'+
      '<!ENTITY delta    "&#948;" >'+
      '<!ENTITY epsilon  "&#949;" >'+
      '<!ENTITY zeta     "&#950;" >'+
      '<!ENTITY eta      "&#951;" >'+
      '<!ENTITY theta    "&#952;" >'+
      '<!ENTITY iota     "&#953;" >'+
      '<!ENTITY kappa    "&#954;" >'+
      '<!ENTITY lambda   "&#955;" >'+
      '<!ENTITY mu       "&#956;" >'+
      '<!ENTITY nu       "&#957;" >'+
      '<!ENTITY xi       "&#958;" >'+
      '<!ENTITY omicron  "&#959;" >'+
      '<!ENTITY pi       "&#960;" >'+
      '<!ENTITY rho      "&#961;" >'+
      '<!ENTITY sigmaf   "&#962;" >'+
      '<!ENTITY sigma    "&#963;" >'+
      '<!ENTITY tau      "&#964;" >'+
      '<!ENTITY upsilon  "&#965;" >'+
      '<!ENTITY phi      "&#966;" >'+
      '<!ENTITY chi      "&#967;" >'+
      '<!ENTITY psi      "&#968;" >'+
      '<!ENTITY omega    "&#969;" >'+
      '<!ENTITY thetasym "&#977;" >'+
      '<!ENTITY upsih    "&#978;" >'+
      '<!ENTITY piv      "&#982;" >'+
      '<!ENTITY bull     "&#8226;" >'+
      '<!ENTITY hellip   "&#8230;" >'+
      '<!ENTITY prime    "&#8242;" >'+
      '<!ENTITY Prime    "&#8243;" >'+
      '<!ENTITY oline    "&#8254;" >'+
      '<!ENTITY frasl    "&#8260;" >'+
      '<!ENTITY weierp   "&#8472;" >'+
      '<!ENTITY image    "&#8465;" >'+
      '<!ENTITY real     "&#8476;" >'+
      '<!ENTITY trade    "&#8482;" >'+
      '<!ENTITY alefsym  "&#8501;" >'+
      '<!ENTITY larr     "&#8592;" >'+
      '<!ENTITY uarr     "&#8593;" >'+
      '<!ENTITY rarr     "&#8594;" >'+
      '<!ENTITY darr     "&#8595;" >'+
      '<!ENTITY harr     "&#8596;" >'+
      '<!ENTITY crarr    "&#8629;" >'+
      '<!ENTITY lArr     "&#8656;" >'+
      '<!ENTITY uArr     "&#8657;" >'+
      '<!ENTITY rArr     "&#8658;" >'+
      '<!ENTITY dArr     "&#8659;" >'+
      '<!ENTITY hArr     "&#8660;" >'+
      '<!ENTITY forall   "&#8704;" >'+
      '<!ENTITY part     "&#8706;" >'+
      '<!ENTITY exist    "&#8707;" >'+
      '<!ENTITY empty    "&#8709;" >'+
      '<!ENTITY nabla    "&#8711;" >'+
      '<!ENTITY isin     "&#8712;" >'+
      '<!ENTITY notin    "&#8713;" >'+
      '<!ENTITY ni       "&#8715;" >'+
      '<!ENTITY prod     "&#8719;" >'+
      '<!ENTITY sum      "&#8721;" >'+
      '<!ENTITY minus    "&#8722;" >'+
      '<!ENTITY lowast   "&#8727;" >'+
      '<!ENTITY radic    "&#8730;" >'+
      '<!ENTITY prop     "&#8733;" >'+
      '<!ENTITY infin    "&#8734;" >'+
      '<!ENTITY ang      "&#8736;" >'+
      '<!ENTITY and      "&#8743;" >'+
      '<!ENTITY or       "&#8744;" >'+
      '<!ENTITY cap      "&#8745;" >'+
      '<!ENTITY cup      "&#8746;" >'+
      '<!ENTITY int      "&#8747;" >'+
      '<!ENTITY there4   "&#8756;" >'+
      '<!ENTITY sim      "&#8764;" >'+
      '<!ENTITY cong     "&#8773;" >'+
      '<!ENTITY asymp    "&#8776;" >'+
      '<!ENTITY ne       "&#8800;" >'+
      '<!ENTITY equiv    "&#8801;" >'+
      '<!ENTITY le       "&#8804;" >'+
      '<!ENTITY ge       "&#8805;" >'+
      '<!ENTITY sub      "&#8834;" >'+
      '<!ENTITY sup      "&#8835;" >'+
      '<!ENTITY nsub     "&#8836;" >'+
      '<!ENTITY sube     "&#8838;" >'+
      '<!ENTITY supe     "&#8839;" >'+
      '<!ENTITY oplus    "&#8853;" >'+
      '<!ENTITY otimes   "&#8855;" >'+
      '<!ENTITY perp     "&#8869;" >'+
      '<!ENTITY sdot     "&#8901;" >'+
      '<!ENTITY lceil    "&#8968;" >'+
      '<!ENTITY rceil    "&#8969;" >'+
      '<!ENTITY lfloor   "&#8970;" >'+
      '<!ENTITY rfloor   "&#8971;" >'+
      '<!ENTITY lang     "&#9001;" >'+
      '<!ENTITY rang     "&#9002;" >'+
      '<!ENTITY loz      "&#9674;" >'+
      '<!ENTITY spades   "&#9824;" >'+
      '<!ENTITY clubs    "&#9827;" >'+
      '<!ENTITY hearts   "&#9829;" >'+
      '<!ENTITY diams    "&#9830;" >]>'+
      '<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">'+
      '<xsl:output method="html" omit-xml-declaration="yes" encoding="ISO-8859-1" doctype-public="-//W3C//DTD XHTML 1.0 Transitional//EN" doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"/>'+
      '<xsl:template match="/"><html><head><meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />'+
      '<meta http-equiv="X-UA-Compatible" content="IE=10,chrome=1" />',
    initBody:'</head><body>',
    endBody:'</body></html></xsl:template>',
    endEnvelope:'</xsl:stylesheet>',
    header:'',
    footer:'<div class="footer notprint">'+
        '<div class="nave">'+
          '<div class="lfooter"></div>'+
          '<div id="containerQrcode"></div>'+
          '<div class="rfooter">'+
          '<div class="contraProva">'+
            '<div class="label"><xsl:value-of select="rows/print_report/name_contraprova"/></div>'+
            '<div class="val"><xsl:value-of select="rows/print_report/value_contraprova"/></div>'+
          '</div>'+
          '<div class="userprint"><span><xsl:value-of select="rows/print_report/user_print"/></span>'+
          '/<span><xsl:value-of select="rows/print_report/data_print"/></span></div>'+
        '</div></div>'+
      '</div>',
    includCss:'<link media="all" rel="stylesheet" href="{rows/print_report/link_img}extensions/webReport/css/webReportPrint.css?v='+IGRP_returnTime()+'"/>',
    includeJs:'<script type="text/javascript" src="{rows/print_report/link_img}js/jquery-2.1.1.min.js"></script>'+
      '<script type="text/javascript"> var qrcodeResult = '+"'"+'<xsl:value-of select="rows/print_report/link_qrcode"/>'+"'"+';'+
      'var containerQrcode = "#containerQrcode";</script>'+
      '<script type="text/javascript" src="{rows/print_report/link_img}extensions/qrcode/qrcode.min.js"></script>'+
      '<script type="text/javascript" src="{rows/print_report/link_img}extensions/qrcode/qrcode.init.js"></script>', 
    includJsCharts:'<script type="text/javascript" src="{rows/print_report/link_img}extensions/highcharts/js/highcharts.js"></script>'+
      '<script type="text/javascript" src="{rows/print_report/link_img}extensions/highcharts/js/highcharts-more.js"></script>',
    includTmplCharts:'<xsl:include href="{rows/print_report/link_img}xsl/tmpl/IGRP-graph.tmpl.xsl?v='+IGRP_returnTime()+'"/>'  
  }
};