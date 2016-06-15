var fs = require('fs');
const readline = require('readline');

const rl = readline.createInterface({
	input: process.stdin,
	output: process.stdout
});

eval(fs.readFileSync('RTG.js') + '');

rl.question('How many triangles of each type do you want create?', (answer) => {
	var myRTG = new RTG();

	for (var i = 0; i < answer; i++) {
		var equilateral = myRTG._equilateral();
		var scalene = myRTG._scalene();
		var isocele = myRTG._isocele();

		var equilateralPoints = equilateral.points();
		var scalenePoints = scalene.points();
		var isocelePoints = isocele.points();
		console.log('Equilateral:');
		console.log(equilateralPoints);

		console.log('Scalene:');
		console.log(scalenePoints);

		console.log('Isocele:');
		console.log(isocelePoints);
	}
	rl.close();
});