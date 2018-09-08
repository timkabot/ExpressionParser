#include <iostream>
#include <string>
class Program
{
	int main()
	{
		String input;
		cin>>input;
		Parser parser = new Parser(input);
		Expressino expressionTree = parser.parse();
		return 0;
	}
}
