package com.vgoryashko.hibernate.carsstore.models.items;

/**
 * Class that implements Photo model.
 *
 * @author Vlad Goryashko
 * @version 0.1
 * @since 3/23/18
 */
public class Photo {

    private long id;

    private Advertisement advertisement;

    private String fileName;

    public Photo() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Advertisement getAdvertisement() {
        return advertisement;
    }

    public void setAdvertisement(Advertisement advertisement) {
        this.advertisement = advertisement;
    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Photo)) {
            return false;
        }

        Photo photo = (Photo) o;

        if (!advertisement.equals(photo.advertisement)) {
            return false;
        }
        return fileName.equals(photo.fileName);
    }

    @Override
    public int hashCode() {
        int result = advertisement.hashCode();
        result = 31 * result + fileName.hashCode();
        return result;
    }
}
