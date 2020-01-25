package com.example.ecom.searchableActivity.models;

import java.util.List;
//import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

//@Generated("com.robohorse.robopojogenerator")
public class SearchResponse{

	@SerializedName("facetQueryResult")
	private FacetQueryResult facetQueryResult;

	@SerializedName("last")
	private boolean last;

	@SerializedName("highlighted")
	private List<Object> highlighted;

	@SerializedName("pageable")
	private Pageable pageable;

	@SerializedName("sort")
	private Sort sort;

	@SerializedName("maxScore")
	private Object maxScore;

	@SerializedName("facetFields")
	private List<Object> facetFields;

	@SerializedName("content")
	private List<ContentItem> content;

	@SerializedName("totalElements")
	private int totalElements;

	@SerializedName("empty")
	private boolean empty;

	@SerializedName("facetResultPages")
	private List<Object> facetResultPages;

	@SerializedName("number")
	private int number;

	@SerializedName("size")
	private int size;

	@SerializedName("numberOfElements")
	private int numberOfElements;

	@SerializedName("totalPages")
	private int totalPages;

	@SerializedName("alternatives")
	private List<Object> alternatives;

	@SerializedName("suggestions")
	private List<Object> suggestions;

	@SerializedName("allFacets")
	private List<Object> allFacets;

	@SerializedName("fieldStatsResults")
	private FieldStatsResults fieldStatsResults;

	@SerializedName("facetPivotFields")
	private List<Object> facetPivotFields;

	@SerializedName("first")
	private boolean first;

	public void setFacetQueryResult(FacetQueryResult facetQueryResult){
		this.facetQueryResult = facetQueryResult;
	}

	public FacetQueryResult getFacetQueryResult(){
		return facetQueryResult;
	}

	public void setLast(boolean last){
		this.last = last;
	}

	public boolean isLast(){
		return last;
	}

	public void setHighlighted(List<Object> highlighted){
		this.highlighted = highlighted;
	}

	public List<Object> getHighlighted(){
		return highlighted;
	}

	public void setPageable(Pageable pageable){
		this.pageable = pageable;
	}

	public Pageable getPageable(){
		return pageable;
	}

	public void setSort(Sort sort){
		this.sort = sort;
	}

	public Sort getSort(){
		return sort;
	}

	public void setMaxScore(Object maxScore){
		this.maxScore = maxScore;
	}

	public Object getMaxScore(){
		return maxScore;
	}

	public void setFacetFields(List<Object> facetFields){
		this.facetFields = facetFields;
	}

	public List<Object> getFacetFields(){
		return facetFields;
	}

	public void setContent(List<ContentItem> content){
		this.content = content;
	}

	public List<ContentItem> getContent(){
		return content;
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

	public void setFacetResultPages(List<Object> facetResultPages){
		this.facetResultPages = facetResultPages;
	}

	public List<Object> getFacetResultPages(){
		return facetResultPages;
	}

	public void setNumber(int number){
		this.number = number;
	}

	public int getNumber(){
		return number;
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

	public void setAlternatives(List<Object> alternatives){
		this.alternatives = alternatives;
	}

	public List<Object> getAlternatives(){
		return alternatives;
	}

	public void setSuggestions(List<Object> suggestions){
		this.suggestions = suggestions;
	}

	public List<Object> getSuggestions(){
		return suggestions;
	}

	public void setAllFacets(List<Object> allFacets){
		this.allFacets = allFacets;
	}

	public List<Object> getAllFacets(){
		return allFacets;
	}

	public void setFieldStatsResults(FieldStatsResults fieldStatsResults){
		this.fieldStatsResults = fieldStatsResults;
	}

	public FieldStatsResults getFieldStatsResults(){
		return fieldStatsResults;
	}

	public void setFacetPivotFields(List<Object> facetPivotFields){
		this.facetPivotFields = facetPivotFields;
	}

	public List<Object> getFacetPivotFields(){
		return facetPivotFields;
	}

	public void setFirst(boolean first){
		this.first = first;
	}

	public boolean isFirst(){
		return first;
	}

	@Override
 	public String toString(){
		return 
			"SearchResponse{" + 
			"facetQueryResult = '" + facetQueryResult + '\'' + 
			",last = '" + last + '\'' + 
			",highlighted = '" + highlighted + '\'' + 
			",pageable = '" + pageable + '\'' + 
			",sort = '" + sort + '\'' + 
			",maxScore = '" + maxScore + '\'' + 
			",facetFields = '" + facetFields + '\'' + 
			",content = '" + content + '\'' + 
			",totalElements = '" + totalElements + '\'' + 
			",empty = '" + empty + '\'' + 
			",facetResultPages = '" + facetResultPages + '\'' + 
			",number = '" + number + '\'' + 
			",size = '" + size + '\'' + 
			",numberOfElements = '" + numberOfElements + '\'' + 
			",totalPages = '" + totalPages + '\'' + 
			",alternatives = '" + alternatives + '\'' + 
			",suggestions = '" + suggestions + '\'' + 
			",allFacets = '" + allFacets + '\'' + 
			",fieldStatsResults = '" + fieldStatsResults + '\'' + 
			",facetPivotFields = '" + facetPivotFields + '\'' + 
			",first = '" + first + '\'' + 
			"}";
		}
}