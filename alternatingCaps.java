//ThIs iS WhAt tHe pRoGrAm sPiTs oUt wHeN I InPuT ThIs sEnTeNcE I HoPe yOu fInD ThIs hElPfUl
public static String alternatingCaps(String word) {
		for(int i = 0; i < word.length(); i += 2)
			word = word.substring(0, i) + word.substring(i, i+1).toUpperCase() + word.substring(i + 1);
		return word;
}
