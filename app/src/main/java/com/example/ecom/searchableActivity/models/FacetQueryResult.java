package com.example.ecom.searchableActivity.models;

import java.util.List;
//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class FacetQueryResult{

	@SerializedName("number")
	private int number;

	@SerializedName("last")
	private boolean last;

	@SerializedName("size")
	private int size;

	@SerializedName("numberOfElements")
	private int numberOfElements;

	@SerializedName("totalPages")
	private int totalPages;

	@SerializedName("pageable")
	private String pageable;

	@SerializedName("sort")
	private Sort sort;

	@SerializedName("content")
	private List<Object> content;

	@SerializedName("first")
	private boolean first;

	@SerializedName("totalElements")
	private int totalElements;

	@SerializedName("empty")
	private boolean empty;

	public void setNumber(int number){
		this.number = number;
	}

	public int getNumber(){
		return number;
	}

	public void setLast(boolean last){
		this.last = last;
	}

	public boolean isLast(){
		return last;
	}

	public void setSize(int size){
		this.size = size;
	}

	public int getSize(){
		return size;
	}

	public void setNumberOfElements(int numberOfElements){
		this.numberOfElements = numberOfElements;
	}

	public int getNumberOfElements(){
		return numberOfElements;
	}

	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}

	public int getTotalPages(){
		return totalPages;
	}

	public void setPageable(String pageable){
		this.pageable = pageable;
	}

	public String getPageable(){
		return pageable;
	}

	public void setSort(Sort sort){
		this.sort = sort;
	}

	public Sort getSort(){
		return sort;
	}

	public void setContent(List<Object> content){
		this.content = content;
	}

	public List<Object> getContent(){
		return content;
	}

	public void setFirst(boolean first){
		this.first = first;
	}

	public boolean isFirst(){
		return first;
	}

	public void setTotalElements(int totalElements){
		this.totalElements = totalElements;
	}

	public int getTotalElements(){
		return totalElements;
	}

	public void setEmpty(boolean empty){
		this.empty = empty;
	}

	public boolean isEmpty(){
		return empty;
	}

	@Override
 	public String toString(){
		return 
			"FacetQueryResult{" + 
			"number = '" + number + '\'' + 
			",last = '" + last + '\'' + 
			",size = '" + size + '\'' + 
			",numberOfElements = '" + numberOfElements + '\'' + 
			",totalPages = '" + totalPages + '\'' + 
			",pageable = '" + pageable + '\'' + 
			",sort = '" + sort + '\'' + 
			",content = '" + content + '\'' + 
			",first = '" + first + '\'' + 
			",totalElements = '" + totalElements + '\'' + 
			",empty = '" + empty + '\'' + 
			"}";
		}
}