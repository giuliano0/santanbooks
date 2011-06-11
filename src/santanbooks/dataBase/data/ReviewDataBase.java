package data;

import Entity.Review;

public class ReviewDataBase extends DataBase implements IReviewDataBase{
	
	ReviewDataBase(){
		super();
		this.folder = "src/data/review/";
		this.uniqueKey = true;
	}

	public Review getByIdentifier(String identifier) {
		return (Review)super.get(identifier);
	}
	
	public Review getNext(){
		return (Review)super.getNext();
	}

	public boolean insert(Review Review) {
		return super.insert(Review);
	}

	public boolean update(Review Review) {
		return super.update(Review);
	}
}
