/*
 * (C) Copyright 2005 Davide Brugali, Marco Torchiano
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
 * 02111-1307  USA
 */
package model;

import java.util.*;

/**
 * The multiformat calculator
 */
public class Calculator {
  private Rational operand_0 = new Rational();
  private Rational operand_1 = new Rational();
  private Stack<Rational> operands = new Stack<Rational>();
  
  // The current format of the calculator
  private Format format = new FixedPointFormat();
  // The current numberbase of the calculator
  private Base base = new DecimalBase();

  public void addOperand(String newOperand) throws FormatException {
	  operands.push(format.parse(newOperand, base));
  }

  public void add(){
	Rational result = new Rational();
	while(!operands.empty()){
		result = result.plus(operands.pop());
	}
	operands.push(result);
  }
  public void subtract() {
	  Rational result = new Rational();
	  while(!operands.empty()){
		result = result.minus(operands.pop());
	  }
	  operands.push(result);
  }
  public void multiply() {
	  if(!operands.empty()){
		  Rational result = operands.pop();
		  while(!operands.empty()){
				result = result.mul(operands.pop());
		  }
		  operands.push(result);
	  }else{
		  operands.push(new Rational());
	  }
  }
  public void divide() {
	  if(!operands.empty()){
		  Rational result = operands.pop();
		  while(!operands.empty()){
				result = result.div(operands.pop());
		  }
		  operands.push(result);
	  }else{
		  operands.push(new Rational());
	  }
  }

  public String getOperand(){
	if(operands.empty()){
		return "";
	}else{
		return format.toString(operands.get(countOperands()-1),base);
	}
  }
  
  public String getOperands(){
	  String temp = "";
	  for(Rational operand : operands){
		  temp += format.toString(operand, base) + " ";
	  }
	  return temp;
  }
  
  public void clear(){
	  while(!operands.empty()){
		  operands.pop();
	  }
	  setBase(new DecimalBase());
	  setFormat(new FixedPointFormat());
  }
  public int countOperands(){
	  return operands.size();
  }

  public void setBase(Base newBase){
    base = newBase;
  }
  public Base getBase(){
    return base;
  }
  public void setFormat(Format newFormat){
    format = newFormat;
  }
  public Format getFormat(){
    return format;
  }
}