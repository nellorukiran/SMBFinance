<%@ page import="java.io.*,java.util.*" %>
<%@ page import="javax.servlet.*,java.text.*" %>
<%@page import="com.smb.util.CommonUtil"%>
<%
CommonUtil commonUtil = new CommonUtil();
String contextPath = request.getContextPath();
String scriptName ="",userType ="";Date dNow = null ;SimpleDateFormat ft = null;
try{
	scriptName = commonUtil.getProperty("script.Name");
	application.setAttribute("script.Name", scriptName);
	userType =(String) request.getAttribute("USER_TYPE");
	application.setAttribute("USER_TYPE", userType);
	
		dNow = new Date( );
		ft = new SimpleDateFormat ("EEE MMM dd yyyy");
	   
	   //out.print( "<h2 align=\"center\">" + ft.format(dNow) + "</h2>");
}catch(Exception e){
	
}

%>
<link rel="stylesheet" href="<%=contextPath%>/css/photoGalary.css">
<SCRIPT type="text/javascript">document.getElementsByTagName('html')[0].className = 'jsEnabled';</SCRIPT>
<script type="text/javascript">if(typeof(jQuery) != "undefined"){jQuery.noConflict();}//For no Coflicts with mootools and Jquery</script>
<script type="text/javascript" src="<%=contextPath%>/js/photoGalary/BrowserDetect.js"></script> 
<script type="text/javascript" src="<%=contextPath%>/js/photoGalary/mootools-core_all.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/photoGalary/navigation_new.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/photoGalary/galleryRollover.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/photoGalary/Moo3DCarousel.1.0.js"></script>
<script type="text/javascript" src="<%=contextPath%>/js/photoGalary/carouselExtend.js"></script> 
<div class="regOutDiv">
	<div class="regInnerDiv">
		<div style="width: 725px;float:left">
			<div class="gallery-block"> <!--gallery-block start  -->
					<div id="homeColumns"><!-- homeColumns start -->
						<div class="contentContainer"><!-- contentContainer start -->
							<div class="subModuleContent removePadding" id="subModuleContent"><!-- subModuleContent removePadding start -->
								<div id="homeGalleryDots" class="homeGalleryDots"></div>
								<div class="homeGalleryWrap" style="left: 12px; top: 0px; width: 650px; height: 227px">
								<!-- homeGalleryWrap start  -->
									<div class="galleryLeftBtn" id="goLeft">
										<img border="0" alt="Previous" src="<%=contextPath%>/images/galleryHomeLeft.jpg">
									</div>
									<div class="homeGalleryCarousel" id="homeGalleryCarousel">
										<a href="<%=contextPath%>/images/items/AC2.jpg"><img border="0" alt="" align="left" src="<%=contextPath%>/images/items/AC2.jpg" width="350" height="215"></a>
										<a href="<%=contextPath%>/images/items/DOUBLECOT1.jpg"><img border="0" alt="" align="left" src="<%=contextPath%>/images/items/DOUBLECOT1.jpg" width="350" height="215"></a>
										<a href="<%=contextPath%>/images/items/DOUBLECOT2.jpg"><img border="0" alt="" align="left" src="<%=contextPath%>/images/items/DOUBLECOT2.jpg" width="350" height="215"></a>
										<a href="<%=contextPath%>/images/items/FRIDGE1.jpg"><img border="0" alt="" align="left" src="<%=contextPath%>/images/items/FRIDGE1.jpg" width="350" height="215"></a>
										<a href="<%=contextPath%>/images/items/FRIDGE2.jpg"><img border="0" alt="" align="left" src="<%=contextPath%>/images/items/FRIDGE2.jpg" width="350" height="215"></a>
										<a href="<%=contextPath%>/images/items/Sofa1.jpg"><img border="0" alt="" align="left" src="<%=contextPath%>/images/items/Sofa1.jpg" width="350" height="215"></a>
										<a href="<%=contextPath%>/images/items/AC2.jpg"><img border="0" alt="" align="left" src="<%=contextPath%>/images/items/AC2.jpg" width="350" height="215"></a>										
										<a href="<%=contextPath%>/images/items/Sofa2.jpg"><img border="0" alt="" align="left" src="<%=contextPath%>/images/items/Sofa2.jpg" width="350" height="215"></a>
										<a href="<%=contextPath%>/images/items/Sofa3.jpg"><img border="0" alt="" align="left" src="<%=contextPath%>/images/items/Sofa3.jpg" width="350" height="215"></a>
										<a href="<%=contextPath%>/images/items/TV Stand1.jpg"><img border="0" alt="" align="left" src="<%=contextPath%>/images/items/TV Stand1.jpg" width="350" height="215"></a>
										<a href="<%=contextPath%>/images/items/TV1.jpg"><img border="0" alt="" align="left" src="<%=contextPath%>/images/items/TV1.jpg" width="350" height="215"></a>
										<a href="<%=contextPath%>/images/items/TV2.jpg"><img border="0" alt="" align="left" src="<%=contextPath%>/images/items/TV2.jpg" width="350" height="215"></a>	
									</div>
									<div class="galleryRightBtn" id="goRight">
										<img border="0" alt="Next" src="<%=contextPath%>/images/galleryHomeRight.jpg">
									</div>
								</div>
								<div class="homeGalleryBtn" id="homeGalleryBtn" style="">
									<a id="homeGalleryCaption" class="preventBehaviour moreBtn moreBtnLight lightBtnText" onclick="javascript: if (window.pageTracker) { pageTracker._trackPageview('/2010Homepage/Video'); }" href="#">Abu Dhabi 2010 - onboard lap</a>
								</div>
								<!-- homeGalleryBtn end -->
							</div><!-- subModuleContent removePadding end -->
						</div> <!-- contentContainer end -->
					</div> <!-- homeColumns end -->
			</div> <!-- gallery-block end -->
		<div style="width:725px;float:left;font-family: arial;">
			<h2>Welcome to SMB Finance Service</h2>
			<p>Posted by Kiran on <%=ft.format(dNow)%></p>
			<div>
				<p>
				SMB Finance service, is an Indian Non-Banking Financial Company (NBFC). 
The company deals in Dealer and Customers Finance.
 They are a consumer focused company with emphasis on profitable growth and operational 
efficiency to deliver best results to all customers. 
				</p>
				<p>The company is a 8-year-old most diversified non-bank in Andrapradesh. Apart from being the largest financier of customers durables in Andrapradesh they are also one of the most profitable firms in this category.

Head Office in Nellore , Kamakshi Tower , Near to Narthaki Theater, the company has 4500+ customers and 15 dealers points in town.</p>
			</div>
		</div>
		</div><!-- registerDiv -->
	</div><!-- regInnerDiv -->
</div><!-- regOutDiv -->
<script type=text/javascript>
window.addEvent('domready', function() {
	$$('.homeFeaturedEachWrap').addEvents({
		mouseover: function(){
			this.addClass('homeFeaturedEachWrapOver');
		},
		mouseout: function(){
			this.removeClass('homeFeaturedEachWrapOver');
		},
		click: function(){
			var linkUrl = this.getElement('h3').getElement('a');
			location.href = linkUrl;
		}
	});
});
window.addEvent('domready', function() {            
var uniqueId = 0;
$$('#homeGalleryCarousel a').each(function(i) {
	var dot = new Element('span', {
		html: '&#9679;',
		id: 'dot' + uniqueId,
		styles: {
			color: '#cccccc'
		}
	});

	dot.inject($('homeGalleryDots'));
	uniqueId++;

});

var homeGalleryCarousel = new HomeGalleryCarousel('homeGalleryCarousel', {
	centerOffset: { x: -5, y: 0 },
	xRadius: 190,
	yRadius: -30,
	rotateDuration: 180,
	autoRun: false,
	interval: 200,
	powerExponent: 1,
	trigo: true
},
			'homeGalleryCaption'
		);

$$('#homeGalleryCarousel a').each(function(elem) {
	homeGalleryCarousel.addElement(elem);
});

$("goLeft").addEvent('click', function() { homeGalleryCarousel.goNext(); });
$("goRight").addEvent('click', function() { homeGalleryCarousel.goPrevious(); });
});
</script>