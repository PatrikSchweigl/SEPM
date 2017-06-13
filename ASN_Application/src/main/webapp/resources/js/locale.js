PrimeFaces.locales['de'] = {
	closeText : 'Schließen',
	prevText : 'Zurück',
	nextText : 'Weiter',
	monthNames : [ 'Januar', 'Februar', 'März', 'April', 'Mai', 'Juni', 'Juli',
			'August', 'September', 'Oktober', 'November', 'Dezember' ],
	monthNamesShort : [ 'Jan', 'Feb', 'MÃ¤r', 'Apr', 'Mai', 'Jun', 'Jul',
			'Aug', 'Sep', 'Okt', 'Nov', 'Dez' ],
	dayNames : [ 'Sonntag', 'Montag', 'Dienstag', 'Mittwoch', 'Donnerstag',
			'Freitag', 'Samstag' ],
	dayNamesShort : [ 'So', 'Mo', 'Di', 'Mi', 'Do', 'Fr', 'Sa' ],
	dayNamesMin : [ 'S', 'M', 'D', 'M ', 'D', 'F ', 'S' ],
	weekHeader : 'Woche',
	firstDay : 1,
	isRTL : false,
	showMonthAfterYear : false,
	yearSuffix : '',
	timeOnlyTitle : 'Wähle Zeit',
	timeText : 'Zeit',
	hourText : 'Stunde',
	minuteText : 'Minute',
	secondText : 'Sekunde',
	currentText : 'Aktuelles Datum',
	ampm : false,
	month : 'Monat',
	week : 'Woche',
	day : 'Tag',
	allDayText : 'Ganzer Tag'
};

function noWeekend(date)
{
  var day = date.getDay();
  return [day != 0 && day != 6, '']
}
