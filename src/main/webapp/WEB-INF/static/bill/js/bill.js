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
					var tdMain = document.createElement('div');
					var tdPlus = document.createElement('div');
					var tdManus = document.createElement('div');
					tdMain.setAttribute('class', 'tdMain');
					tdPlus.setAttribute('class', 'tdPlus');
					tdManus.setAttribute('class', 'tdManus');
					tdPlus.setAttribute('data-cost', list[z]);
					tdManus.setAttribute('data-cost', list[z]);
					tdMain.appendChild(document.createTextNode('td' + list[z]));
					tdPlus.setAttribute('onclick', 'plusCost(this)');
					tdManus.setAttribute('onclick', 'manusCost(this)');
					crTd.appendChild(tdMain);
					crTd.appendChild(tdPlus);
					crTd.appendChild(tdManus);

					crTd.setAttribute('id', 'menu');

					crTr.appendChild(crTd);
				}
			}
		}
		table.appendChild(crTr);
	}
}

// 금액계산
function plusCost(thisDiv) {
	var totalCost = document.getElementById('totalCost');
	var plusCost = 0;
	var tableCost =
		thisDiv.parentNode.parentNode.parentNode.parentNode.querySelector(
			'.totalCost'
		);

	plusCost =
		parseInt(tableCost.value) + parseInt(thisDiv.getAttribute('data-cost'));

	tableCost.value = plusCost;
	totalCost.innerHTML = tableCost.value;
}

function manusCost(thisDiv) {
	var totalCost = document.getElementById('totalCost');
	var plusCost = 0;
	var tableCost =
		thisDiv.parentNode.parentNode.parentNode.parentNode.querySelector(
			'.totalCost'
		);

	plusCost =
		parseInt(tableCost.value) - parseInt(thisDiv.getAttribute('data-cost'));

	if (plusCost < 0) {
		plusCost = 0;
	}

	tableCost.value = plusCost;
	totalCost.innerHTML = tableCost.value;
}
//테이블 별 금액노출
function getTableCost(thisTable) {
	var tableCost = document
		.getElementById(thisTable)
		.querySelector('.totalCost');
	var totalCost = document.getElementById('totalCost');

	totalCost.innerHTML = tableCost.value;
}

//data 통신으로 table 호출
function getTalbeList() {
	var table = document.getElementsByClassName('crTClass');

	for (var i = 0; i < table.length; i++) {
		console.log(table[i]);
		drowTable(table[i], list);
	}
}
