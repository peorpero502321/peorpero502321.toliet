const tabList = document.querySelectorAll('.tab_menu .list li');
const contents = document.querySelectorAll('.tab_menu .cont_area .cont');
let activeCont = ''; // 현재 활성화 된 컨텐츠 (기본:#tab1 활성화)

for (var i = 0; i < tabList.length; i++) {
	tabList[i].querySelector('.btn').addEventListener('click', function (e) {
		e.preventDefault();
		for (var j = 0; j < tabList.length; j++) {
			// 나머지 버튼 클래스 제거
			tabList[j].classList.remove('is_on');

			// 나머지 컨텐츠 display:none 처리
			contents[j].style.display = 'none';
		}

		// 버튼 관련 이벤트
		this.parentNode.classList.add('is_on');

		// 버튼 클릭시 컨텐츠 전환
		activeCont = this.getAttribute('href');
		document.querySelector(activeCont).style.display = 'block';
	});
}

// 반복 돌려서 table 뽑기
var list = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

// 테이블그리기
function drowTable(table, list) {
	var z = 0;
	var crTable = document.createElement('table');
	var crTr;

	for (var i = 1; i <= list.length; i += 2) {
		// tr 생성
		crTr = document.createElement('tr');

		if (0 != i) {
			for (z; z <= i; z++) {
				if (z < list.length) {
					var crTd = document.createElement('td');
					crTd.appendChild(document.createTextNode('td' + list[z]));
					crTd.setAttribute('id', 'menu');
					crTd.setAttribute('data-cost', list[z]);
					crTd.setAttribute('onclick', 'sellCost(this)');
					crTr.appendChild(crTd);
				}
			}
		}
		table.appendChild(crTr);
	}
}

function sellCost(thisTr) {
	var totalCost = document.getElementById('totalCost');
	var plusCost = 0;
	plusCost =
		parseInt(totalCost.innerHTML) + parseInt(thisTr.getAttribute('data-cost'));
	totalCost.innerHTML = plusCost;
	console.log(plusCost);
}

//data 통신으로 table 호출
function getTalbeList() {
	var table = document.getElementsByClassName('crTClass');

	for (var i = 0; i < table.length; i++) {
		console.log(table[i]);
		drowTable(table[i], list);
	}
}
