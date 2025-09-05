import requests

BASE_URL = "https://jsonplaceholder.typicode.com"

def test_get_posts():
    resp = requests.get(f"{BASE_URL}/posts")
    assert resp.status_code == 200
    data = resp.json()
    assert isinstance(data, list)
    assert len(data) > 0
    assert "userId" in data[0]

def test_get_post_by_id():
    resp = requests.get(f"{BASE_URL}/posts/1")
    assert resp.status_code == 200
    post = resp.json()
    assert post["id"] == 1
    assert post["title"]

def test_create_post():
    payload = {"title": "foo", "body": "bar", "userId": 1}
    resp = requests.post(f"{BASE_URL}/posts", json=payload)
    assert resp.status_code == 201
    post = resp.json()
    assert post["title"] == "foo"
    assert "id" in post
