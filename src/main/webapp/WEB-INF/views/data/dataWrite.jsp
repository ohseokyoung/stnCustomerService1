<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="/campus/css/ckeditor.css"/>
<script src="https://cdn.ckeditor.com/ckeditor5/39.0.1/super-build/ckeditor.js"></script>
<script src="/campus/js/ckeditor.js"></script>
<style>
	#subject{width: 1000px;}
</style>
<script>
	window.onload=function(){
		CKEDITOR.ClassicEditor.create(document.getElementById("content"),option);
	}
	
	$(function(){
		//파일첨부 추가하기
		//document하고 온클릭해야 추가된 +에도 또 click이 적용된다.document가 페이지 전체이므로 그냥 onclick()으로하면 생성만되고 +기능은 못사용한다.
		$(document).on('click',"input[value=' + ']",function(){
			//이벤트가 발생한(this) 객체의 부모의 부머에 마지막에 추가
			$(this).parent().parent().append('<div><input type="file" name="filename"/><input type="button" value=" + "/></div>'); //방금일어난 이벤트의 부모의 부모 즉  li안에 추가
			//이벤트가 발생한(this) +버튼의 value를 -로 변경하기
			$(this).val(' - ');
		});
		//첨부파일 삭제하기
		$(document).on('click',"input[value=' - ']",function(){
			$(this).parent().remove();
		})
		
		$("#dataForm").submit(function(){
			if($("#subject").val()==""){
				alert("제목을 입력하세요.");
				return false;
			}
			//첨부파일의 갯수 구하기 -반드시 1개이상 첨부해야함
			let fileCount=0;
			$("input[name=filename]").each(function(){	
				if($(this).val()!=""){
					fileCount++;
				}
			});
			
			if(fileCount<1){
				alert("1개이상의 파일을 첨부하여야 합니다.");
				return false;
			}
			//-----추가하기-----
			return true;
		});
	});
</script>

<main>
<h1>자료 올리기 폼</h1>
	<!-- file첨부가 있는 form태그는 시작태그에  enctype속성(enctype="multipart/form-data")이 반드시 있어야 서버로 파일을 보낼 수 있다. -->
	<form method="post" id="dataForm"  action="${pageContext.servletContext.contextPath}/data/writeOk" enctype="multipart/form-data">
		<ul>
			<li>제목</li>
			<li><input type="text" name="subject" id="subject"></li>
			<li>글내용</li>
			<li><textarea name="content" id="content"></textarea></li>
			
			<!-- 첨부파일 -->
			<li>첨부파일</li>
			<li>
				<div><input type="file" name="filename"/><input type="button" value=" + "/></div>
			</li>
			
			
			<li style="padding-top:10px;">
				<input type="submit" value="자료올리기"/>
				<input type="reset" value="다시쓰기"/>
			</li>
		</ul>
	</form>
</main>
