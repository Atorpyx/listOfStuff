//without mod
public static boolean primeNum(int num) {
	double numholder = num-1;
	while((num/numholder)/(int)(num/numholder) != 1 && numholder != 1)
		numholder--;
	if(numholder == 1 && num > 2)
		return true;
	return false;
} 
//with mod
public static boolean primeNum(int num) {
	double numholder = num-1;
	while(num % numholder != 0 && numholder > 1)
		numholder--;
	if(numholder == 1 && num > 2)
		return true;
	return false;
}
