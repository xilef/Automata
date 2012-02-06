import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.StringTokenizer;

import javax.swing.*;
import javax.swing.text.BadLocationException;

class Rules {
	String current_state = new String();
	String read = new String();
	String pop = new String();
	String next_state = new String();
	String push = new String();
	
	public Rules(String in[]){
		current_state = in[0];
		read = in[1];
		pop = in[2];
		next_state = in[3];
		push = in[4];
	}
}
class Simulation {
	Stack <String> stack = new Stack <String> ();
	String state;
	char input[];
	int rule_num;
}
class CFG {
	String left;
	String right;
	
	public CFG(String in[]) {
		left = in[0];
		right = in[1];
	}
}

public class automata {
	static ArrayList <String> nonTerminal = new ArrayList <String> ();
	static ArrayList <String> Terminal = new ArrayList <String> ();
	static JFrame window = new JFrame();
   	static JPanel contentPane = new JPanel();
   	static final JTextArea simulation = new JTextArea();
	static Rules rules[] = new Rules[13];
	static Simulation simulate = new Simulation();
	static CFG cfg[];
	static String grammar[][];
	static String startState = "";
    public automata() {
    	createGUI();
    	window.setLayout(null);
    	window.setVisible(true);
    	window.pack();
    	window.setTitle("Pushdown Automata v.0");
		window.setLocation( new Point( 0, 0 ) );
		window.setSize( new Dimension( 900, 650 ) );
		window.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE );
    }
        
	public static void cout(Object x){
		System.out.println(x);
	}
    
	public static void addComponent(Container pane, Component object, int x, int y, int w, int h) {
		pane.add( object );
		object.setBounds(x, y, w, h);
	}
	
	public static void createGUI(){
		final JTextField boxes[] = new JTextField[3];
		final JScrollPane boxSPane[] = new JScrollPane[3];
		final JScrollPane macSPane[] = new JScrollPane[3];
		final JTextArea iRules = new JTextArea();
    	final JTextArea oRules = new JTextArea();
		final JLabel label[] = new JLabel[8];
    	final JLabel display[] = new JLabel[9];
    	final JLabel enterSim = new JLabel("Enter string to simulate: ");
    	final JLabel outSim = new JLabel("Simulation: ");
    	final JTextField machineD[] = new JTextField[5];
    	final JTextField inString = new JTextField();
    	final JButton go = new JButton( "Go");
    	final JButton sim = new JButton( "Simulate");
    	
    	
    	JSeparator divider = new JSeparator(SwingConstants.VERTICAL);
    	JSeparator divider2 = new JSeparator(SwingConstants.VERTICAL);
    	
    	JScrollPane ruleSPane = new JScrollPane( iRules );
    	JScrollPane oruleSPane = new JScrollPane( oRules );
    	JScrollPane simSPane = new JScrollPane( simulation );
    	
    	for(int x = 0; x < 5; x++){
			machineD[x] = new JTextField();
			machineD[x].setVisible( true );
			machineD[x].setEditable( false );
    	}
    	
    	for(int x = 0; x < 3; x++){
			boxes[x] = new JTextField();
			boxes[x].setVisible( true );
			boxSPane[x] = new JScrollPane( boxes[x] );
			boxSPane[x].setVisible( true );
			boxSPane[x].setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			macSPane[x] = new JScrollPane( machineD[x] );
			macSPane[x].setVisible( true );
			macSPane[x].setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    	}
    	
    	
    	
    	for(int x = 0; x < 8; x++){
			label[x] = new JLabel();
    		label[x].setVisible( true );
    	}
    	
    	for(int x = 0; x < 9; x++){
    		display[x] = new JLabel();
    		display[x].setVisible( true );
    	}
    	
    	
    	label[0].setText("G = (V, E, R, S)");
    	label[1].setText("(Separate multiple values with spaces.)");
    	
    	label[2].setText("Non-Terminal Symbols (V): ");
    	label[3].setText("Terminal Symbols (E): ");
    	label[4].setText("Starting State (S): ");
    	label[5].setText("Rules (R): ");
    	label[6].setText("Note: 'e' represents empty");
    	label[7].setText("Ex.  E = E + T");
    	
    	display[0].setText("M = (k, E, r, ^, S, F)");
    	display[1].setText("States (k) : ");
    	display[2].setText("Symbols (E) : ");
    	display[3].setText("Non-terminal Symbols (r) : ");
    	display[4].setText("Starting                          Accepting ");
    	display[5].setText("State (S):                        State (F):");
    	display[6].setText("Transitions (^) :");
    	display[7].setText("' ' represents empty.");
    	display[8].setText("'~' represents a symbol.");
    	
    	iRules.setVisible( true );
    	go.setVisible( true );
    	divider.setVisible( true );
    	enterSim.setVisible( true );
    	inString.setVisible( true );
    	inString.setEnabled( false );
    	contentPane.setLayout( null );
    	sim.setEnabled( false );
    	simulation.setEditable( false );
    	
    	addComponent( contentPane, label[0], 80,8,800,20 );
    	addComponent( contentPane, label[1], 9,28,400,20 );
    	
    	addComponent( contentPane, label[2], 10,55,180,25 );
    	addComponent( contentPane, label[3], 10,128,180,25 );
    	addComponent( contentPane, label[4], 10,198,180,25 );
    	addComponent( contentPane, label[5], 10,272,180,25 );
    	
    	/*
    	 * Note kay input rules
    	 */
    	addComponent( contentPane, label[6], 40,285,180,40 );
    	addComponent( contentPane, label[7], 40,307,180,40 );
    	
    	/*
    	 * input na textbox
    	 */
    	addComponent( contentPane, boxSPane[0], 40,83,180,40 );
    	addComponent( contentPane, boxSPane[1], 40,155,180,40 );
    	addComponent( contentPane, boxSPane[2], 40,227,180,40 );
    	
    	addComponent( contentPane, ruleSPane, 40,350,180,200);
    	addComponent( contentPane, simSPane, 550,160,300,390);
    	addComponent( contentPane, oruleSPane, 310, 350, 180, 200 );
    	
    	addComponent( contentPane, go, 80,575,80,20 );
    	addComponent( contentPane, divider, 255,35,1,520);
    	addComponent( contentPane, divider2, 513,35,1,520);
    	
    	addComponent( contentPane, enterSim, 550,28,180,20 );
    	addComponent( contentPane, inString, 605,58,180,20 );
    	addComponent( contentPane, sim, 643,90,100,20 );
    	addComponent( contentPane, outSim, 550,125,180,20 );
    	
    	addComponent( contentPane, display[0], 350,8,400,20 );
    	addComponent( contentPane, display[1], 280,28,180,25 );
    	
    	addComponent( contentPane, display[2], 280,95,180,25 );
    	addComponent( contentPane, display[3], 280,160,180,25 );
    	addComponent( contentPane, display[4], 280,239,350,25 );
    	addComponent( contentPane, display[5], 280,259,180,25 );
    	addComponent( contentPane, display[6], 280,310,180,25 );
    	
    	addComponent( contentPane, macSPane[0], 310,55,180,40 );
    	addComponent( contentPane, macSPane[1], 310,120,180,40 );
    	addComponent( contentPane, macSPane[2], 310,193,180,40 );
    	addComponent( contentPane, machineD[3], 310,285,50,20 );
    	addComponent( contentPane, machineD[4], 437,285,50,20 );
    	
    	
    	
    	go.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nTerminal = boxes[0].getText();
				String terminal = boxes[1].getText();
				String sState = boxes[2].getText();
				String inRules = iRules.getText();
				ArrayList <String> terminalList = new ArrayList <String> ();
				ArrayList <String> nterminalList = new ArrayList <String> ();
				ArrayList <String> tempLeft = new ArrayList <String> ();
				ArrayList <String> tempRight = new ArrayList <String> ();
				boolean error = false;
				nonTerminal.clear();
				if (nTerminal.length() < 1 || terminal.length() < 1 || sState.length() < 1 || inRules.length() < 1)
					JOptionPane.showMessageDialog(null, "May kulang po.");
				else{
					StringTokenizer st1 = new StringTokenizer(nTerminal," ");
					StringTokenizer st2 = new StringTokenizer(terminal," ");
					
					while(st1.hasMoreTokens()){
						String in = st1.nextToken();
						if (!nterminalList.contains(in))
							nterminalList.add(in);
					}
					
					while(st2.hasMoreTokens()){
						String in = st2.nextToken();
						if (!terminalList.contains(in))
							terminalList.add(in);
					}
					
					if(!nterminalList.contains(sState)){
						JOptionPane.showMessageDialog(null, "Invalid starting state.");
						error = true;
					}
					else{
						startState = sState;
						cout("Start state: "+startState);
						machineD[3].setText(startState);
					}
					
					int ctr = 0;
					while(ctr < nterminalList.size()){
						nonTerminal.add(nterminalList.get(ctr).toString());
						cout("Nonterminal: "+nterminalList.get(ctr).toString());
						ctr++;
					}
					ctr = 0;
					while(ctr < terminalList.size()){
						Terminal.add(terminalList.get(ctr).toString());
						cout("Terminal: "+terminalList.get(ctr).toString());
						ctr++;
					}
					ctr = 0;
					String text = iRules.getText();
					
					while (ctr < iRules.getLineCount()) {
						int start, end;
						try {
							start = iRules.getLineStartOffset(ctr);
					        end   = iRules.getLineEndOffset(ctr);
						} catch (BadLocationException e1) {
							e1.printStackTrace();
							return;
						}
				        String line = text.substring(start, end);
				        if (!line.contains("=")) {
							JOptionPane.showMessageDialog(null, "Invalid rule at line "+(ctr+1)+".");
				        	error = true;
				        	return;
				        }
				        else {
					        StringTokenizer a = new StringTokenizer(line, "=");
					        String left = "";
					        String right = "";
					        if (a.hasMoreTokens())
					        	left = a.nextToken();
					        if (a.hasMoreTokens())
					        	right = a.nextToken();
					        
					        for (int num = 0; num < left.length(); num++) {
					        	if (left.charAt(num) != ' ') {
					        		if (!nterminalList.contains(String.valueOf(left.charAt(num)))) {
										JOptionPane.showMessageDialog(null, "Invalid non-terminal character at line "+(ctr+1)+".");
					        			error = true;
					        			break;
					        		}
					        	}
					        }
					        
					        StringTokenizer b = new StringTokenizer(right,"|");
					        while(b.hasMoreTokens()){
					        String tRight = b.nextToken();
						        for (int num = 0; num < tRight.length(); num++) {
						        	if (tRight.charAt(num) != ' ' && tRight.charAt(num) != '\n') {
						        		if (Character.isUpperCase(tRight.charAt(num))) {
						        			if (!nterminalList.contains(String.valueOf(tRight.charAt(num)))) {
												JOptionPane.showMessageDialog(null, "Invalid non-terminal character at line "+(ctr+1)+".");
						        				error = true;
						        				break;
						        			}
						        		}
						        		else if (Character.isLowerCase(tRight.charAt(num))) {
						        			if (!terminalList.contains(String.valueOf(tRight.charAt(num))) && tRight.charAt(num) != 'e') {
												JOptionPane.showMessageDialog(null, "Invalid terminal character at line "+(ctr+1)+".");
						        				error = true;
						        				break;
						        			}
						        		}
						        		
						        		else {
						        			if (!terminalList.contains(String.valueOf(tRight.charAt(num)))) {
												JOptionPane.showMessageDialog(null, "Invalid terminal character at line "+(ctr+1)+".");
						        				error = true;
						        				break;
						        			}
						        		}
						        	}
						        }
						        if (!error) {
						        	String tempL = left.replaceAll(" ", "");
						        	String tempR = tRight.replaceAll(" ", "");
						        	tempLeft.add(tempL.replaceAll("\n", ""));
						        	tempRight.add(tempR.replaceAll("\n", ""));
						        	/*grammar[ctr][0] = tempL.replaceAll("\n", "");
						        	grammar[ctr][1] = tempR.replaceAll("\n", "");*/                                                                   
						        }
					        
					        } //while
					        
				        }
				        ctr++;
					}
				
				if ( !error ){
					ArrayList <String> stateList = new ArrayList <String> ();
					inString.setEnabled( true );
					sim.setEnabled( true );
					/*boxes[0].setEditable( false );
					boxes[1].setEditable( false );
					boxes[2].setEditable( false );
					iRules.setEditable( false );
					go.setEnabled( false );*/
					
					machineD[4].setText("q$");
					oRules.setText("");
					grammar = new String[tempLeft.size()][2];
			        for(int x = 0; x < tempLeft.size(); x++){
			        	grammar[x][0] = tempLeft.get(x).toString();
			        	grammar[x][1] = tempRight.get(x).toString();
			        }
			        
			        createCFG();
					createRules();
					
					for (int x = 0; x < rules.length; x++){
						oRules.append(x+1 + ": (" + rules[x].current_state + "," + rules[x].read + "," + rules[x].pop + ") , (" + rules[x].next_state + "," + rules[x].push + ")\n");
						if (!stateList.contains(rules[x].current_state)){
							stateList.add(rules[x].current_state);
						}
					}
					
					machineD[0].setText(stateList.toString().substring(1,stateList.toString().length()-1));
					machineD[1].setText(terminal.replace(' ',','));
					machineD[2].setText(nonTerminal.toString().substring(1,nonTerminal.toString().length()-1));
					contentPane.repaint();
					
					}
				}
			}
    	});
    	
    	sim.addActionListener(new ActionListener(){
    		public void actionPerformed( ActionEvent e){
    			if ( inString.getText().length() < 1 )
    				JOptionPane.showMessageDialog(null, "Nothing to simulate.");
    			else{
    				simulation.setText("");
    				String t = inString.getText();
    				if (!t.endsWith("$"))
    					t = t+"$";
    				simulate(t);
    			}
    		}
    	});
    	
    	addComponent( window, contentPane, 0,0,1024,800 );
    }
	
	private static void createRules() {
		rules = new Rules[cfg.length + 3];
		String t[] = new String[4];
		rules[0] = new Rules(new String[] {"p"," "," ","q",startState});
		rules[1] = new Rules(new String[] {"q","~"," ","q~"," "});
		rules[2] = new Rules(new String[] {"q~"," ","~","q"," "});
		for (int ctr = 0; ctr < cfg.length; ctr++) {
			if (Terminal.contains(String.valueOf(cfg[ctr].right.charAt(0))))
				t[0] = t[2] = "q" + String.valueOf(cfg[ctr].right.charAt(0));
			else
				t[0] = t[2] = "q~";
			t[1] = cfg[ctr].left;
			t[3] = cfg[ctr].right;
			
			if(t[3].equals("e")){
				t[0] = t[2] = "q~";
				t[3] = " ";
			}
			
			rules[ctr + 3] = new Rules(new String[] {t[0], " ", t[1], t[2], t[3]});
		}
	}
        
    private static void createCFG(){
    	ArrayList <String> temp = new ArrayList <String> ();
    	ArrayList <String> newGL = new ArrayList <String> ();
    	ArrayList <String> newGR = new ArrayList <String> ();
    	ArrayList <String> tmpp = new ArrayList <String> ();
    	ArrayList <String> tmpn = new ArrayList <String> ();
    	ArrayList <String> t = new ArrayList <String> ();

    	System.out.println("create cfg daw.");
        int ctr = 0, iCtr;
        
        while(ctr<grammar.length){
        	temp.clear();
        	int index = 0;
        	String var = "";
        	
        	while (index < newGL.size() && String.valueOf(grammar[ctr][1].toString().charAt(0)).equals(newGL.get(index).toString())) {
        		var = newGR.get(index).toString() + grammar[ctr][1].substring(1);
        		temp.add(var);
        		index++;
        		if (index == newGL.size())
        			index = 0;
        	}
        	if (var.equals(""))
        		temp.add(grammar[ctr][1]);
        	
        	iCtr = ctr + 1;
        	      	
        	while(iCtr < grammar.length /*&& grammar[ctr][0].equals(grammar[iCtr][0])*/){
        		if (grammar[ctr][0].equals(grammar[iCtr][0])){
	        		index = 0;
	        		var = "";
	        		for (index = 0; index < newGL.size(); index++) {
	        			if (String.valueOf(grammar[iCtr][1].toString().charAt(0)).equals(newGL.get(index).toString())) {
	        				var = newGR.get(index).toString() + grammar[iCtr][1].substring(1);
	                		temp.add(var);
	        			}
	        		}
	            	if (var.equals(""))
	            		temp.add(grammar[iCtr][1]);
	            	
	        		//temp.add(grammar[iCtr][1]);
	        		iCtr++;
	        		if (iCtr == grammar.length) {
	        			for (int index1 = 0; index1 < temp.size(); index1++) {
	        				var = "";
	        				for (index = 0; index < newGL.size(); index++) {
	                			if (String.valueOf(temp.get(index1).toString().charAt(0)).equals(newGL.get(index).toString())) {
	                				var = newGR.get(index).toString() + temp.get(index1).toString().substring(1);
	                        		temp.add(var);
	                        		temp.remove(index1);
	                			}
	                		}
	            			if (!var.equals(""))
	            				index1 = -1;
	        			}
	        		}
        		}
        		else
        			iCtr++;
        		
        	}
        	
        	
        	//process si temp
        	String tRule = "";
        	tmpp.clear();
        	tmpn.clear();
        	boolean isRecursive = false;
            for(int j = 0; j<temp.size(); j++){
        		if (grammar[ctr][0].equals(String.valueOf(temp.get(j).toString().charAt(0)))){
        			tmpp.add(temp.get(j));
        			isRecursive = true;
        			Random a = new Random();
        			do {
        				char b = (char) (a.nextInt(26) + 65);
        				tRule = String.valueOf(b);
        			} while (newGL.contains(tRule) || grammar[ctr][0].equals(tRule));
        		}
        		else {
        			tmpn.add(temp.get(j));
        		}
        	}
        	
        	for (int j = 0; j < tmpn.size(); j++) {
        		String newR = tmpn.get(j).toString();
        		int cIndex = 2;
        		t.clear();
        		for (int i = j + 1; i < tmpn.size(); i++) {
        			 if (tmpn.get(j).toString().startsWith(tmpn.get(i).toString().substring(0, 1).toString())) {
        				 if (!t.contains(tmpn.get(j))) {
        					 t.add(tmpn.get(j));
        				 }
        				 if (!t.contains(tmpn.get(i))) {
        					 t.add(tmpn.get(i));
        					 tmpn.remove(i);
        					 i--;
        				 }
        			 }
        		}
        		if (!t.isEmpty())
        			tmpn.remove(j);
    			for (int f = 1; f < t.size(); f++) {
    				if (t.get(0).toString().startsWith(t.get(f).toString().substring(0, cIndex).toString())) {
    					if (f + 1 >= t.size()){
    						cIndex++;
    						f = 1;
    					}
    				}
    				else
    					break;
    			}
        		
        		if (!t.isEmpty()) {
        			Random a = new Random();
        			String trule;
        			do {
        				char b = (char) (a.nextInt(26) + 65);
        				trule = String.valueOf(b);
        			} while (newGL.contains(trule) || grammar[ctr][0].equals(trule));
        			newGR.add(t.get(0).toString().substring(0, cIndex - 1) + trule);
	        		newGL.add(grammar[ctr][0]);
	        		if (!nonTerminal.contains(grammar[ctr][0]))
            			nonTerminal.add(grammar[ctr][0]);
	        		
        			for (int i = 0; i < t.size(); i++) {
            			newR = t.get(i).toString().substring(cIndex - 1);
            			if (isRecursive)
            				newR = newR + tRule;
        				newGR.add(newR);
        				newGL.add(trule);
                		if (!nonTerminal.contains(trule))
                			nonTerminal.add(trule);
        			}
        		}
        		else {
	        		newGR.add(newR + tRule);
	        		newGL.add(grammar[ctr][0]);
	        		if (!nonTerminal.contains(grammar[ctr][0]))
	        			nonTerminal.add(grammar[ctr][0]);
        		}
        	}
        	for (int j = 0; j < tmpp.size(); j++) {
        		newGR.add(tmpp.get(j).toString().substring(1) + tRule);
        		newGL.add(tRule);
        		if (!nonTerminal.contains(tRule))
        			nonTerminal.add(tRule);
        	}
        	
        	if(isRecursive){
        		newGR.add(" ");
        		newGL.add(tRule);
        		if (!nonTerminal.contains(tRule))
        			nonTerminal.add(tRule);
        	}
        	ctr += temp.size();
        }
        
        /*for( int x = 0; x<temp.size(); x++){
        	cout("Linked list: "+temp.get(x));
        }

        for( int x = 0; x<newGL.size(); x++){
        	cout("Left: "+newGL.get(x)+" Right: "+newGR.get(x));
        }*/
        cfg = new CFG[newGL.size()];

        for (int x = 0; x < newGL.size(); x++) {
			cfg[x] = new CFG(new String[] {newGL.get(x).toString(),newGR.get(x).toString()});
		}
    }
    
    private static void simulate(String temp) {
		int ctr = 0;
		int sCtr = 0;
		int num = 0;
		boolean symbol = false; 
		simulate.stack.clear();
		simulate.rule_num = 0;
		
    	do{
			if ( sCtr==0 ){
				simulate.state = rules[0].current_state;
				simulate.input = temp.toCharArray();
				simulate.stack.push(" ");
			}
			else {				
				for (num = 0; num < rules.length; num++) {
					if (simulate.state.equals(rules[num].current_state) || rules[num].current_state.equals("q~") ) {
						if (rules[num].pop.equals(simulate.stack.peek()) || rules[num].pop.equals(" ")
								|| rules[num].pop.equals("~") && symbol && String.valueOf(simulate.state.charAt(1)).equals(simulate.stack.peek().toString())) 
							break;
						
					}
				}
				if (num == rules.length) {
					JOptionPane.showMessageDialog(null, "Input string is not accepted according to the rules.");
					cout("Input string is not accepted according to the rules.");
					return;
				}
			
				if( !rules[num].next_state.equals("q~" )){
					simulate.state = rules[num].next_state;
					if ( ( rules[num].next_state.length() != 2 ))
						symbol = false;
					}
				if ( !rules[num].read.equals(" ") ) {
					simulate.state = "q" + simulate.input[ctr];
					symbol = true;
					ctr++;
				}
				
				if (!rules[num].pop.equals(" "))
					simulate.stack.pop();
				if (!rules[num].push.equals(" ")) {
					String tmp = "";
					for (int num1 = rules[num].push.length() - 1; num1 >= 0; num1--) {
						if(rules[num].push.charAt(num1) == '\'') {
							num1--;
							tmp = rules[num].push.substring(num1);
						}
						else
							tmp = String.valueOf(rules[num].push.charAt(num1));
						simulate.stack.push(tmp);
					}
				}
				simulate.rule_num = num + 1;
			}

			simulation.append(sCtr + 1 + ") " + simulate.state + "\t" + temp.substring(ctr) + "\t" + simulate.stack.toString() + "\t" + simulate.rule_num + "\n"/* + symbol + "\n"*/);

			sCtr++;
		}while( !simulate.state.equals("q$") || !(simulate.stack.peek().equals(" ") && sCtr != 1) );
    }
        
	public static void main(String a[]){
		automata j = new automata();
	}	
}