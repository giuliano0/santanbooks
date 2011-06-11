package DataBase;

import Classes.Review;

public class ReviewDataBase extends DataBase implements IReviewDataBase{
	
	ReviewDataBase(){
		super();
		this.folder = "src/data/review/";
		this.uniqueKey = true;
	}

	public Review getByIdentifier(String identifier) {
		if (this.search.equalsIgnoreCase("identifier"))
			return (Review)super.getNext();
		else
			return null;
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