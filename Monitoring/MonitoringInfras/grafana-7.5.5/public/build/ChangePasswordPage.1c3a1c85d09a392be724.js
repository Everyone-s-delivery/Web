(window.webpackJsonp=window.webpackJsonp||[]).push([[14],{EZhu:function(e,t,r){"use strict";r.d(t,"a",(function(){return c}));var n=r("q1tI"),a=r.n(n),o=r("ZFWI"),s=r("kDLi"),i=r("kDDq");function u(){var e=function(e,t){t||(t=e.slice(0));return Object.freeze(Object.defineProperties(e,{raw:{value:Object.freeze(t)}}))}(["\n        max-width: 400px;\n      "]);return u=function(){return e},e}var c=function(e){var t,r=e.user,n=e.onChangePassword,c=e.isSaving,l=o.b.ldapEnabled,d=o.b.authProxyEnabled,f=o.b.disableLoginForm,p=(null===(t=r.authLabels)||void 0===t?void 0:t.length)&&r.authLabels[0];return l||d?a.a.createElement("p",null,"You cannot change password when ldap or auth proxy authentication is enabled."):p&&f?a.a.createElement("p",null,"Password cannot be changed here!"):a.a.createElement("div",{className:Object(i.css)(u())},a.a.createElement(s.Form,{onSubmit:n},(function(e){var t,r,n,i=e.register,u=e.errors,l=e.getValues;return a.a.createElement(a.a.Fragment,null,a.a.createElement(s.Field,{label:"Old password",invalid:!!u.oldPassword,error:null==u||null===(t=u.oldPassword)||void 0===t?void 0:t.message},a.a.createElement(s.Input,{type:"password",name:"oldPassword",ref:i({required:"Old password is required"})})),a.a.createElement(s.Field,{label:"New password",invalid:!!u.newPassword,error:null==u||null===(r=u.newPassword)||void 0===r?void 0:r.message},a.a.createElement(s.Input,{type:"password",name:"newPassword",ref:i({required:"New password is required",validate:{confirm:function(e){return e===l().confirmNew||"Passwords must match"},old:function(e){return e!==l().oldPassword||"New password can't be the same as the old one."}}})})),a.a.createElement(s.Field,{label:"Confirm password",invalid:!!u.confirmNew,error:null==u||null===(n=u.confirmNew)||void 0===n?void 0:n.message},a.a.createElement(s.Input,{type:"password",name:"confirmNew",ref:i({required:"New password confirmation is required",validate:function(e){return e===l().newPassword||"Passwords must match"}})})),a.a.createElement(s.HorizontalGroup,null,a.a.createElement(s.Button,{variant:"primary",disabled:c},"Change Password"),a.a.createElement(s.LinkButton,{variant:"secondary",href:"".concat(o.b.appSubUrl,"/profile")},"Cancel")))})))}},PEdC:function(e,t,r){"use strict";r.r(t),function(e){r.d(t,"ChangePasswordPage",(function(){return p}));var n=r("q1tI"),a=r.n(n),o=r("0cfB"),s=r("/MKj"),i=r("t8hP"),u=r("kDLi"),c=r("lzJ5"),l=r("V9sw"),d=r("ZGyg"),f=r("EZhu"),p=function(e){var t=e.navModel;return a.a.createElement(d.a,{navModel:t},a.a.createElement(l.a,{userId:i.config.bootData.user.id},(function(e,t,r,n,o,s){return a.a.createElement(d.a.Contents,null,a.a.createElement("h3",{className:"page-sub-heading"},"Change Your Password"),t.loadUser?a.a.createElement(u.LoadingPlaceholder,{text:"Loading user profile..."}):a.a.createElement(f.a,{user:s,onChangePassword:e.changePassword,isSaving:t.changePassword}))})))};t.default=Object(o.hot)(e)(Object(s.connect)((function(e){return{navModel:Object(c.a)(e.navIndex,"change-password")}}),{})(p))}.call(this,r("3UD+")(e))},V9sw:function(e,t,r){"use strict";r.d(t,"a",(function(){return v}));var n=r("q1tI"),a=r.n(n),o=r("t8hP"),s=r("ZFWI"),i=r("Obii");function u(e){return(u="function"==typeof Symbol&&"symbol"==typeof Symbol.iterator?function(e){return typeof e}:function(e){return e&&"function"==typeof Symbol&&e.constructor===Symbol&&e!==Symbol.prototype?"symbol":typeof e})(e)}function c(e,t){var r=Object.keys(e);if(Object.getOwnPropertySymbols){var n=Object.getOwnPropertySymbols(e);t&&(n=n.filter((function(t){return Object.getOwnPropertyDescriptor(e,t).enumerable}))),r.push.apply(r,n)}return r}function l(e){for(var t=1;t<arguments.length;t++){var r=null!=arguments[t]?arguments[t]:{};t%2?c(Object(r),!0).forEach((function(t){d(e,t,r[t])})):Object.getOwnPropertyDescriptors?Object.defineProperties(e,Object.getOwnPropertyDescriptors(r)):c(Object(r)).forEach((function(t){Object.defineProperty(e,t,Object.getOwnPropertyDescriptor(r,t))}))}return e}function d(e,t,r){return t in e?Object.defineProperty(e,t,{value:r,enumerable:!0,configurable:!0,writable:!0}):e[t]=r,e}function f(e,t,r,n,a,o,s){try{var i=e[o](s),u=i.value}catch(e){return void r(e)}i.done?t(u):Promise.resolve(u).then(n,a)}function p(e){return function(){var t=this,r=arguments;return new Promise((function(n,a){var o=e.apply(t,r);function s(e){f(o,n,a,s,i,"next",e)}function i(e){f(o,n,a,s,i,"throw",e)}s(void 0)}))}}function g(e,t){if(!(e instanceof t))throw new TypeError("Cannot call a class as a function")}function m(e,t){for(var r=0;r<t.length;r++){var n=t[r];n.enumerable=n.enumerable||!1,n.configurable=!0,"value"in n&&(n.writable=!0),Object.defineProperty(e,n.key,n)}}function w(e,t){return!t||"object"!==u(t)&&"function"!=typeof t?function(e){if(void 0===e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");return e}(e):t}function h(e){return(h=Object.setPrototypeOf?Object.getPrototypeOf:function(e){return e.__proto__||Object.getPrototypeOf(e)})(e)}function b(e,t){return(b=Object.setPrototypeOf||function(e,t){return e.__proto__=t,e})(e,t)}var v=function(e){function t(){var e,r;g(this,t);for(var n=arguments.length,a=new Array(n),u=0;u<n;u++)a[u]=arguments[u];return(r=w(this,(e=h(t)).call.apply(e,[this].concat(a)))).state={teams:[],orgs:[],sessions:[],loadingStates:{changePassword:!1,loadUser:!0,loadTeams:!1,loadOrgs:!1,loadSessions:!1,updateUserProfile:!1,updateUserOrg:!1}},r.changePassword=function(){var e=p(regeneratorRuntime.mark((function e(t){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return r.setState({loadingStates:l({},r.state.loadingStates,{changePassword:!0})}),e.next=3,Object(o.getBackendSrv)().put("/api/user/password",t);case 3:r.setState({loadingStates:l({},r.state.loadingStates,{changePassword:!1})});case 4:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}(),r.loadUser=p(regeneratorRuntime.mark((function e(){var t;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return r.setState({loadingStates:l({},r.state.loadingStates,{loadUser:!0})}),e.next=3,Object(o.getBackendSrv)().get("/api/user");case 3:t=e.sent,r.setState({user:t,loadingStates:l({},r.state.loadingStates,{loadUser:0===Object.keys(t).length})});case 5:case"end":return e.stop()}}),e)}))),r.loadTeams=p(regeneratorRuntime.mark((function e(){var t;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return r.setState({loadingStates:l({},r.state.loadingStates,{loadTeams:!0})}),e.next=3,Object(o.getBackendSrv)().get("/api/user/teams");case 3:t=e.sent,r.setState({teams:t,loadingStates:l({},r.state.loadingStates,{loadTeams:!1})});case 5:case"end":return e.stop()}}),e)}))),r.loadOrgs=p(regeneratorRuntime.mark((function e(){var t;return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return r.setState({loadingStates:l({},r.state.loadingStates,{loadOrgs:!0})}),e.next=3,Object(o.getBackendSrv)().get("/api/user/orgs");case 3:t=e.sent,r.setState({orgs:t,loadingStates:l({},r.state.loadingStates,{loadOrgs:!1})});case 5:case"end":return e.stop()}}),e)}))),r.loadSessions=p(regeneratorRuntime.mark((function e(){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return r.setState({loadingStates:l({},r.state.loadingStates,{loadSessions:!0})}),e.next=3,Object(o.getBackendSrv)().get("/api/user/auth-tokens").then((function(e){e=e.sort((function(e,t){return Number(t.isActive)-Number(e.isActive)})).map((function(e){return{id:e.id,isActive:e.isActive,seenAt:Object(i.dateTimeFormatTimeAgo)(e.seenAt),createdAt:Object(i.dateTimeFormat)(e.createdAt,{format:"MMMM DD, YYYY"}),clientIp:e.clientIp,browser:e.browser,browserVersion:e.browserVersion,os:e.os,osVersion:e.osVersion,device:e.device}})),r.setState({sessions:e,loadingStates:l({},r.state.loadingStates,{loadSessions:!1})})}));case 3:case"end":return e.stop()}}),e)}))),r.revokeUserSession=function(){var e=p(regeneratorRuntime.mark((function e(t){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return e.next=2,Object(o.getBackendSrv)().post("/api/user/revoke-auth-token",{authTokenId:t}).then((function(){var e=r.state.sessions.filter((function(e){return e.id!==t}));r.setState({sessions:e})}));case 2:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}(),r.setUserOrg=function(){var e=p(regeneratorRuntime.mark((function e(t){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return r.setState({loadingStates:l({},r.state.loadingStates,{updateUserOrg:!0})}),e.next=3,Object(o.getBackendSrv)().post("/api/user/using/"+t.orgId,{}).then((function(){window.location.href=s.a.appSubUrl+"/profile"})).finally((function(){r.setState({loadingStates:l({},r.state.loadingStates,{updateUserOrg:!1})})}));case 3:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}(),r.updateUserProfile=function(){var e=p(regeneratorRuntime.mark((function e(t){return regeneratorRuntime.wrap((function(e){for(;;)switch(e.prev=e.next){case 0:return r.setState({loadingStates:l({},r.state.loadingStates,{updateUserProfile:!0})}),e.next=3,Object(o.getBackendSrv)().put("/api/user",t).then(r.loadUser).catch((function(e){return console.error(e)})).finally((function(){r.setState({loadingStates:l({},r.state.loadingStates,{updateUserProfile:!1})})}));case 3:case"end":return e.stop()}}),e)})));return function(t){return e.apply(this,arguments)}}(),r}var r,n,u;return function(e,t){if("function"!=typeof t&&null!==t)throw new TypeError("Super expression must either be null or a function");e.prototype=Object.create(t&&t.prototype,{constructor:{value:e,writable:!0,configurable:!0}}),t&&b(e,t)}(t,e),r=t,(n=[{key:"UNSAFE_componentWillMount",value:function(){this.props.userId&&this.loadUser()}},{key:"render",value:function(){var e=this.props.children,t=this.state,r=t.loadingStates,n=t.teams,o=t.orgs,s=t.sessions,i=t.user,u={changePassword:this.changePassword,loadUser:this.loadUser,loadTeams:this.loadTeams,loadOrgs:this.loadOrgs,loadSessions:this.loadSessions,revokeUserSession:this.revokeUserSession,updateUserProfile:this.updateUserProfile,setUserOrg:this.setUserOrg};return a.a.createElement(a.a.Fragment,null,e(u,r,n,o,s,i))}}])&&m(r.prototype,n),u&&m(r,u),t}(n.PureComponent)},phKE:function(e,t,r){"use strict";r.r(t),r.d(t,"ChangePasswordPage",(function(){return u}));var n=r("q1tI"),a=r.n(n),o=r("NoLC"),s=r("WpK4"),i=r("LOGi"),u=function(){return a.a.createElement(o.b,null,a.a.createElement(o.a,null,a.a.createElement(i.a,null,(function(e){var t=e.changePassword;return a.a.createElement(s.a,{onSubmit:t})}))))};t.default=u}}]);
//# sourceMappingURL=ChangePasswordPage.1c3a1c85d09a392be724.js.map