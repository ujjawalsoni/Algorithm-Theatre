public class Main
{

	public static void main(String[] args)
	{
		
		GUI g = new GUI();
		String content = new StringBuilder()
							.append("mark first element as sorted\n")
							.append("for each unsorted element\n")
							.append("...'extract' the element\n")
							.append("...for i = lastSortedIndex to 0\n")
							.append("......if currentSortedElement > extractedElement\n")
							.append(".........move sorted element to the right by 1\n")
							.append("......else: insert extracted element\n")
							.toString();
		
//		System.out.println(content);
		g.setCodeTraceLabel(content);
	}
}
