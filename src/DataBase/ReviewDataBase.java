package DataBase;

import Classes.Review;

public class ReviewDataBase extends DataBase implements IReviewDataBase{
	
	ReviewDataBase(){
		super();
		this.folder = "src/DataBase/data/review/";
		this.uniqueKey = true;
	}

	public Review getByIdentifier(String identifier) {
		return (Review)super.getNext();
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
