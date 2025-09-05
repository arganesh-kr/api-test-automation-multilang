const request = require("supertest");

const BASE_URL = "https://jsonplaceholder.typicode.com";

describe("Posts API", () => {
  test("GET /posts returns list", async () => {
    const res = await request(BASE_URL).get("/posts");
    expect(res.statusCode).toBe(200);
    expect(res.body.length).toBeGreaterThan(0);
    expect(res.body[0]).toHaveProperty("userId");
  });

  test("GET /posts/1 returns single post", async () => {
    const res = await request(BASE_URL).get("/posts/1");
    expect(res.statusCode).toBe(200);
    expect(res.body.id).toBe(1);
    expect(res.body.title).toBeTruthy();
  });

  test("POST /posts creates a new post", async () => {
    const payload = { title: "foo", body: "bar", userId: 1 };
    const res = await request(BASE_URL).post("/posts").send(payload);
    expect(res.statusCode).toBe(201);
    expect(res.body).toHaveProperty("id");
    expect(res.body.title).toBe("foo");
  });
});
