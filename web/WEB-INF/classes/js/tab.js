	function setTab03Syn ( i )
	{
		selectTab03Syn(i);
	}
	
	function selectTab03Syn ( i )
	{
		switch(i){
			case 1:
			document.getElementById("TabCon1").style.display="block";
			document.getElementById("TabCon2").style.display="none";
			document.getElementById("TabCon3").style.display="none";
			document.getElementById("font1").style.color="#ffffff";
			document.getElementById("font2").style.color="#266dd7";
			document.getElementById("font3").style.color="#266dd7";
			break;
			case 2:
			document.getElementById("TabCon1").style.display="none";
			document.getElementById("TabCon2").style.display="block";
			document.getElementById("TabCon3").style.display="none";
			document.getElementById("font1").style.color="#266dd7";
			document.getElementById("font2").style.color="#ffffff";
			document.getElementById("font3").style.color="#266dd7";
			break;
			case 3:
			document.getElementById("TabCon1").style.display="none";
			document.getElementById("TabCon2").style.display="none";
			document.getElementById("TabCon3").style.display="block";
			document.getElementById("font1").style.color="#266dd7";
			document.getElementById("font2").style.color="#266dd7";
			document.getElementById("font3").style.color="#ffffff";
			break;
		}
	}

