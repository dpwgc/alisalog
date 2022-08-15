import{R as T,j as t,T as b,a as V,D as N,b as d,C as f,S as p,I as C,c as x,B as g,d as F,e as D,f as H,g as w,h as k,L as j,i as L,H as K,N as I,k as B,l as G,m as $,n as U,o as R,p as J}from"./vendor.a4954b2a.js";const q=function(){const e=document.createElement("link").relList;if(e&&e.supports&&e.supports("modulepreload"))return;for(const l of document.querySelectorAll('link[rel="modulepreload"]'))r(l);new MutationObserver(l=>{for(const n of l)if(n.type==="childList")for(const a of n.addedNodes)a.tagName==="LINK"&&a.rel==="modulepreload"&&r(a)}).observe(document,{childList:!0,subtree:!0});function i(l){const n={};return l.integrity&&(n.integrity=l.integrity),l.referrerpolicy&&(n.referrerPolicy=l.referrerpolicy),l.crossorigin==="use-credentials"?n.credentials="include":l.crossorigin==="anonymous"?n.credentials="omit":n.credentials="same-origin",n}function r(l){if(l.ep)return;l.ep=!0;const n=i(l);fetch(l.href,n)}};q();const{Column:y}=k,v="/monitor";class Y extends T.Component{init(){const e=this;let i=[],r=[],l=[];fetch(v+"/appId/list").then(n=>{n.json().then(a=>{for(var o=0;o<a.data.length;o++)i.push({value:a.data[o],label:a.data[o],type:"appId"});e.setState({appIdList:i,countData:[{key:"Total hit logs",value:0},{key:"Number of apps",value:i.length},{key:"Number of environments",value:l.length},{key:"Number of data centers",value:r.length}]})})}),fetch(v+"/idc/list").then(n=>{n.json().then(a=>{for(var o=0;o<a.data.length;o++)r.push({value:a.data[o],label:a.data[o],type:"idc"});e.setState({idcList:r,countData:[{key:"Total hit logs",value:0},{key:"Number of apps",value:i.length},{key:"Number of environments",value:l.length},{key:"Number of data centers",value:r.length}]})})}),fetch(v+"/env/list").then(n=>{n.json().then(a=>{for(var o=0;o<a.data.length;o++)l.push({value:a.data[o],label:a.data[o],type:"env"});e.setState({envList:l,countData:[{key:"Total hit logs",value:0},{key:"Number of apps",value:i.length},{key:"Number of environments",value:l.length},{key:"Number of data centers",value:r.length}]})})}),e.setState({logTimeStart:new Date(new Date().getTime()-18e5),logTimeEnd:new Date(new Date().getTime()),levelList:[{value:0,label:"ALL",type:"level"},{value:1,label:"TRACE",type:"level"},{value:2,label:"DEBUG",type:"level"},{value:3,label:"INFO",type:"level"},{value:4,label:"WARN",type:"level"},{value:5,label:"ERROR",type:"level"}]})}onChange(e){const i=this;let r=[],l=[],n=[],a=[];e.type=="env"&&this.setState({env:e.value}),e.type=="host"&&this.setState({host:e.value}),e.type=="subCategory"&&this.setState({subCategory:e.value}),e.type=="level"&&this.setState({level:e.value}),e.type=="appId"&&(this.setState({appId:e.value}),fetch(v+"/module/list?appId="+e.value).then(o=>{o.json().then(s=>{for(var h=0;h<s.data.length;h++)r.push({value:s.data[h],label:s.data[h],type:"module"});i.setState({moduleList:r})})})),e.type=="module"&&(this.setState({module:e.value}),fetch(v+"/category/list?appId="+i.state.appId+"&module="+e.value).then(o=>{o.json().then(s=>{for(var h=0;h<s.data.length;h++)l.push({value:s.data[h],label:s.data[h],type:"category"});i.setState({categoryList:l})})})),e.type=="category"&&(this.setState({category:e.value}),fetch(v+"/subCategory/list?appId="+i.state.appId+"&module="+i.state.module+"&category="+e.value).then(o=>{o.json().then(s=>{for(var h=0;h<s.data.length;h++)n.push({value:s.data[h],label:s.data[h],type:"subCategory"});i.setState({subCategoryList:n})})})),e.type=="idc"&&(this.setState({idc:e.value}),fetch(v+"/host/list?idc="+e.value).then(o=>{o.json().then(s=>{for(var h=0;h<s.data.length;h++)a.push({value:s.data[h],label:s.data[h],type:"host"});i.setState({hostList:a})})}))}tagChange(e,i){this.setState({tag:e})}traceIdChange(e,i){this.setState({traceId:e})}filter1Change(e,i){this.setState({filter1:e})}filter2Change(e,i){this.setState({filter2:e})}keywordChange(e,i){this.setState({keyword:e})}logTimeStartChange(e,i){this.setState({logTimeStart:e})}logTimeEndChange(e,i){this.setState({logTimeEnd:e})}pageIndexChange(e,i){this.setState({pageIndex:e})}pageSizeChange(e,i){this.setState({pageSize:e})}search(){let e=this,i={idc:e.state.idc,host:e.state.host,env:e.state.env,appId:e.state.appId,module:e.state.module,category:e.state.category,subCategory:e.state.subCategory,tag:e.state.tag,traceId:e.state.traceId,filter1:e.state.filter1,filter2:e.state.filter2,level:e.state.level,keyword:e.state.keyword,logTimeStart:e.state.logTimeStart.getTime(),logTimeEnd:e.state.logTimeEnd.getTime(),pageIndex:e.state.pageIndex,pageSize:e.state.pageSize},r=Object.keys(i).map(l=>l+"="+i[l]).join("&");fetch(v+"/log/list?"+r,{method:"get"}).then(l=>{let n=[];l.json().then(a=>{let o={};for(var s=0;s<a.data.list.length;s++){let c=new Date(a.data.list[s].logTime);var h=c.getFullYear()+"-",O=(c.getMonth()+1<10?"0"+(c.getMonth()+1):c.getMonth()+1)+"-",z=(c.getDate()<10?"0"+c.getDate():c.getDate())+" ",W=(c.getHours()<10?"0"+c.getHours():c.getHours())+":",A=(c.getMinutes()<10?"0"+c.getMinutes():c.getMinutes())+":",M=c.getSeconds()<10?"0"+c.getSeconds():c.getSeconds();a.data.list[s].logTime=h+O+z+W+A+M;let u="";switch(a.data.list[s].level){case 1:u="green",a.data.list[s].level="TRACE",a.data.list[s].level=t(b,{color:u,type:"solid",children:a.data.list[s].level});break;case 2:u="blue",a.data.list[s].level="DEBUG",a.data.list[s].level=t(b,{color:u,type:"solid",children:a.data.list[s].level});break;case 3:u="grey",a.data.list[s].level="INFO",a.data.list[s].level=t(b,{color:u,type:"solid",children:a.data.list[s].level});break;case 4:u="yellow",a.data.list[s].level="WARN",a.data.list[s].level=t(b,{color:u,type:"solid",children:a.data.list[s].level});break;case 5:u="red",a.data.list[s].level="ERROR",a.data.list[s].level=t(b,{color:u,type:"solid",children:a.data.list[s].level});break}n.push(t(V,{name:"log",style:{fontWeight:600,fontSize:"16px"},src:a.data.list[s],displayDataTypes:!1,theme:"monokai"})),o[s]=[{key:"level",value:a.data.list[s].level},{key:"id",value:a.data.list[s].id},{key:"traceId",value:a.data.list[s].traceId},{key:"title",value:a.data.list[s].title},{key:"content",value:a.data.list[s].content},{key:"remarks",value:a.data.list[s].remarks},{key:"tag",value:a.data.list[s].tag},{key:"filter1",value:a.data.list[s].filter1},{key:"filter2",value:a.data.list[s].filter2},{key:"module",value:a.data.list[s].module},{key:"category",value:a.data.list[s].category},{key:"subCategory",value:a.data.list[s].subCategory},{key:"file",value:a.data.list[s].file},{key:"position",value:a.data.list[s].position},{key:"idc",value:a.data.list[s].idc},{key:"host",value:a.data.list[s].host},{key:"appId",value:a.data.list[s].appId},{key:"env",value:a.data.list[s].env},{key:"logTime",value:a.data.list[s].logTime}]}let P=[{key:"Total hit logs",value:a.data.total},{key:"Number of apps",value:e.state.appIdList.length},{key:"Number of environments",value:e.state.envList.length},{key:"Number of data centers",value:e.state.idcList.length}];this.setState({expandData:o,logList:a.data.list,jsonLogList:n,countData:P})})})}expandRowRender(e,i){return t(N,{align:"left",data:this.state.expandData[i]})}setDateTime(e,i){this.setState({logTimeStart:new Date(new Date().getTime()-i),logTimeEnd:new Date(new Date().getTime())})}constructor(e){super(e);this.state={idc:"",host:"",env:"",appId:"",module:"",category:"",subCategory:"",tag:"",traceId:"",filter1:"",filter2:"",level:0,keyword:"",logTimeStart:0,logTimeEnd:0,pageIndex:0,pageSize:50,idcList:[],hostList:[],envList:[],appIdList:[],moduleList:[],categoryList:[],subCategoryList:[],levelList:[],logList:[],jsonLogList:[t("h2",{})],expandData:{},countData:[]},this.tagChange=this.tagChange.bind(this),this.traceIdChange=this.traceIdChange.bind(this),this.filter1Change=this.filter1Change.bind(this),this.filter2Change=this.filter2Change.bind(this),this.logTimeStartChange=this.logTimeStartChange.bind(this),this.logTimeEndChange=this.logTimeEndChange.bind(this),this.pageIndexChange=this.pageIndexChange.bind(this),this.pageSizeChange=this.pageSizeChange.bind(this),this.keywordChange=this.keywordChange.bind(this),this.setDateTime=this.setDateTime.bind(this)}componentDidMount(){this.init()}render(){return d("div",{children:[d(f,{className:"site-home-card",children:[d("div",{className:"site-home",children:[t(p,{className:"site-home-input",prefix:"AppId",onChangeWithObject:!0,optionList:this.state.appIdList,defaultValue:this.state.appIdList[0],onChange:this.onChange.bind(this)}),t("p",{style:{marginRight:"20px"}}),t(p,{className:"site-home-input",prefix:"Module",onChangeWithObject:!0,optionList:this.state.moduleList,defaultValue:this.state.moduleList[0],onChange:this.onChange.bind(this)}),t(p,{className:"site-home-input",prefix:"Category",onChangeWithObject:!0,optionList:this.state.categoryList,defaultValue:this.state.categoryList[0],onChange:this.onChange.bind(this)}),t(p,{className:"site-home-input",prefix:"SubCategory",onChangeWithObject:!0,optionList:this.state.subCategoryList,defaultValue:this.state.subCategoryList[0],onChange:this.onChange.bind(this)}),t("p",{style:{marginRight:"20px"}}),t(p,{className:"site-home-input",prefix:"IDC",onChangeWithObject:!0,optionList:this.state.idcList,defaultValue:this.state.idcList[0],onChange:this.onChange.bind(this)}),t(p,{className:"site-home-input",prefix:"Host",onChangeWithObject:!0,optionList:this.state.hostList,defaultValue:this.state.hostList[0],onChange:this.onChange.bind(this)})]}),d("div",{className:"site-home",children:[t(C,{className:"site-home-input",prefix:"TraceId",showClear:!0,value:this.state.traceId,onChange:this.traceIdChange}),t(C,{className:"site-home-input",prefix:"Tag",showClear:!0,value:this.state.tag,onChange:this.tagChange}),t(C,{className:"site-home-input",prefix:"Filter1",showClear:!0,value:this.state.filter1,onChange:this.filter1Change}),t(C,{className:"site-home-input",prefix:"Filter2",showClear:!0,value:this.state.filter2,onChange:this.filter2Change})]}),d("div",{className:"site-home",children:[t(x,{className:"site-home-input",prefix:"StartTime",type:"dateTime",value:this.state.logTimeStart,onChange:this.logTimeStartChange}),t(x,{className:"site-home-input",prefix:"EndTime",type:"dateTime",value:this.state.logTimeEnd,onChange:this.logTimeEndChange}),t(g,{className:"site-home-input",onClick:e=>this.setDateTime(e,72*60*60*1e3),children:"72 hours"}),t(g,{className:"site-home-input",onClick:e=>this.setDateTime(e,48*60*60*1e3),children:"48 hours"}),t(g,{className:"site-home-input",onClick:e=>this.setDateTime(e,24*60*60*1e3),children:"24 hours"}),t(g,{className:"site-home-input",onClick:e=>this.setDateTime(e,12*60*60*1e3),children:"12 hours"}),t(g,{className:"site-home-input",onClick:e=>this.setDateTime(e,6*60*60*1e3),children:"6 hours"}),t(g,{className:"site-home-input",onClick:e=>this.setDateTime(e,2*60*60*1e3),children:"2 hours"}),t(g,{className:"site-home-input",onClick:e=>this.setDateTime(e,30*60*1e3),children:"30 minutes"})]}),d("div",{className:"site-home",children:[t(p,{className:"site-home-input",prefix:"Env",onChangeWithObject:!0,optionList:this.state.envList,defaultValue:this.state.envList[0],onChange:this.onChange.bind(this)}),t(p,{className:"site-home-input",prefix:"Level",onChangeWithObject:!0,optionList:this.state.levelList,defaultValue:this.state.levelList[0],onChange:this.onChange.bind(this)}),t(C,{className:"site-home-input",prefix:t(F,{}),showClear:!0,value:this.state.keyword,onChange:this.keywordChange}),t(D,{className:"site-home-input",defaultValue:0,min:0,onNumberChange:!0,value:this.state.pageIndex,onChange:this.pageIndexChange}),t(D,{className:"site-home-input",defaultValue:50,min:0,onNumberChange:!0,value:this.state.pageSize,onChange:this.pageSizeChange}),t(g,{className:"site-home-input",onClick:this.search.bind(this),children:"Search"})]})]}),t(f,{className:"site-home-card",children:t(N,{data:this.state.countData,row:!0,size:"medium"})}),t(f,{className:"site-home-card",children:d(H,{type:"button",children:[t(w,{tab:"Table",itemKey:"1",children:d(k,{className:"site-home-table",expandedRowRender:this.expandRowRender.bind(this),hideExpandedColumn:!1,rowKey:"id",dataSource:this.state.logList,pagination:!1,bordered:!0,size:"medium",children:[t(y,{title:"level",dataIndex:"level"},"level"),t(y,{title:"title",dataIndex:"title"},"title"),t(y,{title:"host",dataIndex:"host"},"host"),t(y,{title:"module",dataIndex:"module"},"module"),t(y,{title:"category",dataIndex:"category"},"category"),t(y,{title:"subCategory",dataIndex:"subCategory"},"subCategory"),t(y,{title:"file",dataIndex:"file"},"file"),t(y,{title:"logTime",dataIndex:"logTime"},"logTime")]})}),t(w,{tab:"Json",itemKey:"2",children:this.state.jsonLogList})]})})]})}}const{Column:S}=k,E="/monitor";class Q extends T.Component{init(){const e=this;let i=[],r=[];fetch(E+"/worker/node/list").then(l=>{l.json().then(n=>{i=n.data;let a=[{key:"Number of router nodes",value:r.length},{key:"Number of worker nodes",value:i.length}];e.setState({workerList:i,countData:a})})}),fetch(E+"/router/node/list").then(l=>{l.json().then(n=>{r=n.data;let a=[{key:"Number of router nodes",value:r.length},{key:"Number of worker nodes",value:i.length}];e.setState({countData:a,routerList:r})})})}constructor(e){super(e);this.state={workerList:[],routerList:[],countData:[]}}componentDidMount(){this.init()}render(){return d("div",{children:[t(f,{className:"site-home-card",children:t(N,{data:this.state.countData,row:!0,size:"medium"})}),t(f,{title:"Router nodes",className:"site-home-card",children:t(j,{size:"large",bordered:!0,dataSource:this.state.routerList,renderItem:e=>t(j.Item,{children:e})})}),t(f,{title:"Worker nodes",className:"site-home-card",children:d(k,{className:"site-home-table",dataSource:this.state.workerList,pagination:!1,bordered:!0,size:"medium",children:[t(S,{title:"Address",dataIndex:"address"},"address"),t(S,{title:"UDP Port",dataIndex:"udpPort"},"udpPort"),t(S,{title:"HTTP Port",dataIndex:"httpPort"},"httpPort")]})})]})}}const X=()=>{const m=document.body;m.hasAttribute("theme-mode")?m.removeAttribute("theme-mode"):m.setAttribute("theme-mode","dark")};function Z(){const{Header:m,Sider:e,Content:i,Footer:r}=L;return t(L,{className:"components-layout-demo",style:{height:"100%"},children:d(K,{children:[t(m,{children:t("div",{children:d(I,{mode:"horizontal",children:[t("span",{style:{color:"var(--semi-color-text-2)"},children:t("span",{style:{marginRight:"24px",color:"var(--semi-color-text-0)",fontWeight:"600"},children:"Alisa Log"})}),t(I.Footer,{children:t(g,{theme:"borderless",onClick:X,children:t(B,{})})})]})})}),d(L,{children:[d(L,{children:[t(e,{children:t(I,{defaultOpenKeys:["1"],style:{height:"85vh"},items:[{itemKey:"1",text:"Log",icon:t(G,{}),link:"/"},{itemKey:"2",text:"Node",icon:t($,{}),link:"/#/nodeList"}],onSelect:l=>console.log(l),footer:{collapseButton:!0}})}),t(i,{style:{color:"var(--semi-color-text-0)",backgroundColor:"var( --semi-color-bg-0)"},children:d(U,{children:[t(R,{exact:!0,path:"/",children:t(Y,{})}),t(R,{path:"/nodeList",children:t(Q,{})})]})})]}),d(r,{style:{display:"flex",justifyContent:"space-between",padding:"20px",color:"var(--semi-color-text-2)",backgroundColor:"rgba(var(--semi-grey-0), 1)",height:"10vh"},children:[t("span",{style:{display:"flex",alignItems:"flex-start"},children:t("span",{style:{fontWeight:600},children:"dpwgc / alisalog"})}),d("span",{children:[t("a",{href:"https://github.com/dpwgc/alisalog",style:{marginRight:"12px",color:"rgba(var(--semi-blue-5), 1)",fontWeight:500},children:" Github "}),t("a",{href:"https://gitee.com/dpwgc/alisalog",style:{color:"rgba(var(--semi-blue-5), 1)",fontWeight:500},children:" Gitee "})]})]})]})]})})}J.render(t(Z,{}),document.getElementById("root"));
