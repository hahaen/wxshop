define({ "api": [
  {
    "type": "post",
    "url": "/goods",
    "title": "创建商品",
    "name": "CreateGoods",
    "group": "商品",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Accept",
            "description": "<p>application/json</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Content-Type",
            "description": "<p>application/json</p>"
          }
        ]
      }
    },
    "parameter": {
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n    \"name\": \"肥皂\",\n    \"description\": \"纯天然无污染肥皂\",\n    \"details\": \"这是一块好肥皂\",\n    \"imgUrl\": \"https://img.url\",\n    \"price\": 500,\n    \"stock\": 10,\n    \"shopId\": 12345\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Goods",
            "optional": false,
            "field": "data",
            "description": "<p>创建的商品</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 201 Created\n{\n  \"data\": {\n         \"id\": 12345,\n         \"name\": \"肥皂\",\n         \"description\": \"纯天然无污染肥皂\",\n         \"details\": \"这是一块好肥皂\",\n         \"imgUrl\": \"https://img.url\",\n         \"price\": 500,\n         \"stock\": 10,\n         \"shopId\": 12345,\n         \"createdAt\": \"2022-03-22T13:22:03Z\",\n         \"updatedAt\": \"2022-03-22T13:22:03Z\"\n  }\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "400",
            "description": "<p>Bad Request 若用户的请求中包含错误</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "401",
            "description": "<p>Unauthorized 若用户未登录</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "403",
            "description": "<p>Forbidden 若用户尝试创建非自己管理店铺的商品</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 401 Unauthorized\n{\n  \"message\": \"Unauthorized\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "wxshop-main/src/main/java/com/hahaen/wxshop/controller/GoodsController.java",
    "groupTitle": "商品"
  },
  {
    "type": "delete",
    "url": "/goods/:id",
    "title": "删除商品",
    "name": "DeleteGoods",
    "group": "商品",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Accept",
            "description": "<p>application/json</p>"
          }
        ]
      }
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>商品ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Goods",
            "optional": false,
            "field": "data",
            "description": "<p>被删除的商品</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 204 No Content\n{\n  \"data\": {\n         \"id\": 12345,\n         \"name\": \"肥皂\",\n         \"description\": \"纯天然无污染肥皂\",\n         \"details\": \"这是一块好肥皂\",\n         \"imgUrl\": \"https://img.url\",\n         \"price\": 500,\n         \"stock\": 10,\n         \"createdAt\": \"2022-03-22T13:22:03Z\",\n         \"updatedAt\": \"2022-03-22T13:22:03Z\"\n  }\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "400",
            "description": "<p>Bad Request 若用户的请求中包含错误</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "401",
            "description": "<p>Unauthorized 若用户未登录</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "403",
            "description": "<p>Forbidden 若用户尝试删除非自己管理店铺的商品</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "404",
            "description": "<p>Not Found 若商品未找到</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 401 Unauthorized\n{\n  \"message\": \"Unauthorized\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "wxshop-main/src/main/java/com/hahaen/wxshop/controller/GoodsController.java",
    "groupTitle": "商品"
  },
  {
    "type": "get",
    "url": "/goods",
    "title": "获取所有商品",
    "name": "GetGoods",
    "group": "商品",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Accept",
            "description": "<p>application/json</p>"
          }
        ]
      }
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "pageNum",
            "description": "<p>页数，从1开始</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "pageSize",
            "description": "<p>每页显示的数量</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": true,
            "field": "shopId",
            "description": "<p>店铺ID，若传递，则只显示该店铺中的商品</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "pageNum",
            "description": "<p>页数，从1开始</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "pageSize",
            "description": "<p>每页显示的数量</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "totalPage",
            "description": "<p>共有多少页</p>"
          },
          {
            "group": "Success 200",
            "type": "Goods",
            "optional": false,
            "field": "data",
            "description": "<p>商品列表</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"pageNum\": 1,\n  \"pageSize\": 10,\n  \"totalPage\": 5,\n  \"data\": [\n     {\n         \"id\": 12345,\n         \"name\": \"肥皂\",\n         \"description\": \"纯天然无污染肥皂\",\n         \"details\": \"这是一块好肥皂\",\n         \"imgUrl\": \"https://img.url\",\n         \"price\": 500,\n         \"stock\": 10,\n         \"shopId\": 12345,\n         \"createdAt\": \"2022-03-22T13:22:03Z\",\n         \"updatedAt\": \"2022-03-22T13:22:03Z\"\n     },\n     {\n         ...\n     }\n  ]\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "401",
            "description": "<p>Unauthorized 若用户未登录</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 401 Unauthorized\n{\n  \"message\": \"Unauthorized\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "wxshop-main/src/main/java/com/hahaen/wxshop/controller/GoodsController.java",
    "groupTitle": "商品"
  },
  {
    "type": "get",
    "url": "/goods/:id",
    "title": "获取指定id的商品",
    "name": "GetGoodsById",
    "group": "商品",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Accept",
            "description": "<p>application/json</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Goods",
            "optional": false,
            "field": "data",
            "description": "<p>指定id的商品</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"data\": {\n         \"id\": 12345,\n         \"name\": \"肥皂\",\n         \"description\": \"纯天然无污染肥皂\",\n         \"details\": \"这是一块好肥皂\",\n         \"imgUrl\": \"https://img.url\",\n         \"price\": 500,\n         \"stock\": 10,\n         \"shopId\": 12345,\n         \"createdAt\": \"2022-03-22T13:22:03Z\",\n         \"updatedAt\": \"2022-03-22T13:22:03Z\"\n  }\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "400",
            "description": "<p>Bad Request 若用户的请求中包含错误</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "404",
            "description": "<p>Not Found 商品未找到</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 401 Unauthorized\n{\n  \"message\": \"Unauthorized\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "wxshop-main/src/main/java/com/hahaen/wxshop/controller/GoodsController.java",
    "groupTitle": "商品"
  },
  {
    "type": "patch",
    "url": "/goods/:id",
    "title": "更新商品",
    "name": "UpdateGoods",
    "group": "商品",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Accept",
            "description": "<p>application/json</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Content-Type",
            "description": "<p>application/json</p>"
          }
        ]
      }
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>商品ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n    \"name\": \"肥皂\",\n    \"description\": \"纯天然无污染肥皂\",\n    \"details\": \"这是一块好肥皂\",\n    \"imgUrl\": \"https://img.url\",\n    \"price\": 500,\n    \"stock\": 10\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Goods",
            "optional": false,
            "field": "data",
            "description": "<p>更新后的商品</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"data\": {\n         \"id\": 12345,\n         \"name\": \"肥皂\",\n         \"description\": \"纯天然无污染肥皂\",\n         \"details\": \"这是一块好肥皂\",\n         \"imgUrl\": \"https://img.url\",\n         \"price\": 500,\n         \"stock\": 10,\n         \"createdAt\": \"2022-03-22T13:22:03Z\",\n         \"updatedAt\": \"2022-03-22T13:22:03Z\"\n  }\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "400",
            "description": "<p>Bad Request 若用户的请求中包含错误</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "401",
            "description": "<p>Unauthorized 若用户未登录</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "403",
            "description": "<p>Forbidden 若用户尝试修改非自己管理店铺的商品</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "404",
            "description": "<p>Not Found 若商品未找到</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 401 Unauthorized\n{\n  \"message\": \"Unauthorized\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "wxshop-main/src/main/java/com/hahaen/wxshop/controller/GoodsController.java",
    "groupTitle": "商品"
  },
  {
    "type": "post",
    "url": "/shop",
    "title": "创建店铺",
    "name": "CreateShop",
    "group": "店铺",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Accept",
            "description": "<p>application/json</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Content-Type",
            "description": "<p>application/json</p>"
          }
        ]
      }
    },
    "parameter": {
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n    \"id\": 12345,\n    \"name\": \"我的店铺\",\n    \"description\": \"我的苹果专卖店\",\n    \"imgUrl\": \"https://img.url\",\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 201 Created\n{\n  \"data\": {\n         \"id\": 12345,\n         \"name\": \"我的店铺\",\n         \"description\": \"我的苹果专卖店\",\n         \"imgUrl\": \"https://img.url\",\n         \"ownerUserId\": 12345,\n         \"createdAt\": \"2022-03-22T13:22:03Z\",\n         \"updatedAt\": \"2022-03-22T13:22:03Z\"\n  }\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "400",
            "description": "<p>Bad Request 若用户的请求中包含错误</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "401",
            "description": "<p>Unauthorized 若用户未登录</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 401 Unauthorized\n{\n  \"message\": \"Unauthorized\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "wxshop-main/src/main/java/com/hahaen/wxshop/controller/ShopController.java",
    "groupTitle": "店铺"
  },
  {
    "type": "DELETE",
    "url": "/shop/:id",
    "title": "删除店铺",
    "name": "DeleteShop",
    "group": "店铺",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Accept",
            "description": "<p>application/json</p>"
          }
        ]
      }
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>店铺ID</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 204 No Content\n{\n  \"data\": {\n         \"id\": 12345,\n         \"name\": \"我的店铺\",\n         \"description\": \"我的苹果专卖店\",\n         \"imgUrl\": \"https://img.url\",\n         \"ownerUserId\": 12345,\n         \"createdAt\": \"2022-03-22T13:22:03Z\",\n         \"updatedAt\": \"2022-03-22T13:22:03Z\"\n  }\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "400",
            "description": "<p>Bad Request 若用户的请求中包含错误</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "404",
            "description": "<p>Not Found 若店铺未找到</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "401",
            "description": "<p>Unauthorized 若用户未登录</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "403",
            "description": "<p>Forbidden 若用户尝试删除非自己管理店铺</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 401 Unauthorized\n{\n  \"message\": \"Unauthorized\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "wxshop-main/src/main/java/com/hahaen/wxshop/controller/ShopController.java",
    "groupTitle": "店铺"
  },
  {
    "type": "get",
    "url": "/shop",
    "title": "获取当前用户名下的所有店铺",
    "name": "GetShop",
    "group": "店铺",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Accept",
            "description": "<p>application/json</p>"
          }
        ]
      }
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "pageNum",
            "description": "<p>页数，从1开始</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "pageSize",
            "description": "<p>每页显示的数量</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "pageNum",
            "description": "<p>页数，从1开始</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "pageSize",
            "description": "<p>每页显示的数量</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "totalPage",
            "description": "<p>共有多少页</p>"
          },
          {
            "group": "Success 200",
            "type": "Shop",
            "optional": false,
            "field": "data",
            "description": "<p>店铺列表</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"pageNum\": 1,\n  \"pageSize\": 10,\n  \"totalPage\": 5,\n  \"data\": [\n     {\n         \"id\": 12345,\n         \"name\": \"我的店铺\",\n         \"description\": \"我的苹果专卖店\",\n         \"imgUrl\": \"https://img.url\",\n         \"ownerUserId\": 12345,\n         \"createdAt\": \"2022-03-22T13:22:03Z\",\n         \"updatedAt\": \"2022-03-22T13:22:03Z\"\n     },\n     {\n         ...\n     }\n  ]\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "401",
            "description": "<p>Unauthorized 若用户未登录</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 401 Unauthorized\n{\n  \"message\": \"Unauthorized\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "wxshop-main/src/main/java/com/hahaen/wxshop/controller/ShopController.java",
    "groupTitle": "店铺"
  },
  {
    "type": "get",
    "url": "/shop/:id",
    "title": "获取指定ID的店铺",
    "name": "GetShopById",
    "group": "店铺",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Accept",
            "description": "<p>application/json</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 Created\n{\n  \"data\": {\n         \"id\": 12345,\n         \"name\": \"我的店铺\",\n         \"description\": \"我的苹果专卖店\",\n         \"imgUrl\": \"https://img.url\",\n         \"ownerUserId\": 12345,\n         \"createdAt\": \"2022-03-22T13:22:03Z\",\n         \"updatedAt\": \"2022-03-22T13:22:03Z\"\n         \"updatedAt\": \"2022-03-22T13:22:03Z\"\n         \"updatedAt\": \"2022-03-22T13:22:03Z\"\n  }\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "401",
            "description": "<p>Unauthorized 若用户未登录</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "404",
            "description": "<p>Not found 若店铺未找到</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 401 Unauthorized\n{\n  \"message\": \"Unauthorized\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "wxshop-main/src/main/java/com/hahaen/wxshop/controller/ShopController.java",
    "groupTitle": "店铺"
  },
  {
    "type": "PATCH",
    "url": "/shop/:id",
    "title": "修改店铺",
    "name": "UpdateShop",
    "group": "店铺",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>店铺ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n    \"id\": 12345,\n    \"name\": \"我的店铺\",\n    \"description\": \"我的苹果专卖店\",\n    \"imgUrl\": \"https://img.url\",\n}",
          "type": "json"
        }
      ]
    },
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Accept",
            "description": "<p>application/json</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Content-Type",
            "description": "<p>application/json</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"data\": {\n         \"id\": 12345,\n         \"name\": \"我的店铺\",\n         \"description\": \"我的苹果专卖店\",\n         \"imgUrl\": \"https://img.url\",\n         \"ownerUserId\": 12345,\n         \"createdAt\": \"2022-03-22T13:22:03Z\",\n         \"updatedAt\": \"2022-03-22T13:22:03Z\"\n  }\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "400",
            "description": "<p>Bad Request 若用户的请求中包含错误</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "404",
            "description": "<p>Not Found 若店铺未找到</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "401",
            "description": "<p>Unauthorized 若用户未登录</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "403",
            "description": "<p>Forbidden 若用户尝试修改非自己管理店铺</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 401 Unauthorized\n{\n  \"message\": \"Unauthorized\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "wxshop-main/src/main/java/com/hahaen/wxshop/controller/ShopController.java",
    "groupTitle": "店铺"
  },
  {
    "type": "post",
    "url": "/code",
    "title": "请求验证码",
    "name": "GetCode",
    "group": "登录与鉴权",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Accept",
            "description": "<p>application/json</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Content-Type",
            "description": "<p>application/json</p>"
          }
        ]
      }
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "tel",
            "description": "<p>手机号码</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n    \"tel\": \"13012345678\",\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "400",
            "description": "<p>Bad Request 若用户的请求包含错误</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n  \"message\": \"Bad Request\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "wxshop-main/src/main/java/com/hahaen/wxshop/controller/AuthController.java",
    "groupTitle": "登录与鉴权"
  },
  {
    "type": "post",
    "url": "/login",
    "title": "登录",
    "name": "Login",
    "group": "登录与鉴权",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Accept",
            "description": "<p>application/json</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Content-Type",
            "description": "<p>application/json</p>"
          }
        ]
      }
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "tel",
            "description": "<p>手机号码</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "code",
            "description": "<p>验证码</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n    \"tel\": \"13012345678\",\n    \"code\": \"000000\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "400",
            "description": "<p>Bad Request 若用户的请求包含错误</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "403",
            "description": "<p>Forbidden 若用户的验证码错误</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n  \"message\": \"Bad Request\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "wxshop-main/src/main/java/com/hahaen/wxshop/controller/AuthController.java",
    "groupTitle": "登录与鉴权"
  },
  {
    "type": "post",
    "url": "/logout",
    "title": "登出",
    "name": "Logout",
    "group": "登录与鉴权",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Accept",
            "description": "<p>application/json</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Content-Type",
            "description": "<p>application/json</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "401",
            "description": "<p>Unauthorized 若用户未登录</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n\"message\": \"Bad Request\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "wxshop-main/src/main/java/com/hahaen/wxshop/controller/AuthController.java",
    "groupTitle": "登录与鉴权"
  },
  {
    "type": "get",
    "url": "/status",
    "title": "获取登录状态",
    "name": "Status",
    "group": "登录与鉴权",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Accept",
            "description": "<p>application/json</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Content-Type",
            "description": "<p>application/json</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "User",
            "optional": false,
            "field": "user",
            "description": "<p>用户信息</p>"
          },
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "login",
            "description": "<p>登录状态</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"login\": true,\n  \"user\": {\n      \"id\": 123,\n      \"name\": \"张三\",\n      \"tel\": \"13812345678\",\n      \"avatarUrl\": \"https://url\",\n      \"address\": \"北京市 西城区 100号\",\n  }\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "401",
            "description": "<p>Unauthorized 若用户未登录</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 401 Unauthorized\n{\n  \"message\": \"Unauthorized\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "wxshop-main/src/main/java/com/hahaen/wxshop/controller/AuthController.java",
    "groupTitle": "登录与鉴权"
  },
  {
    "type": "post",
    "url": "/order",
    "title": "下订单",
    "name": "CreateOrder",
    "group": "订单",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Accept",
            "description": "<p>application/json</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Content-Type",
            "description": "<p>application/json</p>"
          }
        ]
      }
    },
    "parameter": {
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n  \"goods\": [\n    {\n        \"id\": 12345,\n        \"number\": 10,\n    },\n    {\n        ...\n    }\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Order",
            "optional": false,
            "field": "data",
            "description": "<p>刚刚创建完成的订单</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 201 Created\n{\n  \"data\": {\n      \"id\": 12345,\n      \"expressCompany\": null,\n      \"expressId\": null,\n      \"status\": \"pending\",\n      \"address\": \"XXX\",\n      \"shop\": {\n         \"id\": 12345,\n         \"name\": \"我的店铺\",\n         \"description\": \"我的苹果专卖店\",\n         \"imgUrl\": \"https://img.url\",\n         \"ownerUserId\": 12345,\n         \"createdAt\": \"2022-03-22T13:22:03Z\",\n         \"updatedAt\": \"2022-03-22T13:22:03Z\"\n       },\n       \"goods\": [\n         {\n             \"id\": 12345,\n             \"name\": \"肥皂\",\n             \"description\": \"纯天然无污染肥皂\",\n             \"details\": \"这是一块好肥皂\",\n             \"imgUrl\": \"https://img.url\",\n             \"address\": \"XXX\",\n             \"price\": 500,\n             \"number\": 10,\n             \"createdAt\": \"2022-03-22T13:22:03Z\",\n             \"updatedAt\": \"2022-03-22T13:22:03Z\"\n         },\n         {\n               ...\n         }\n      ]\n    }\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "400",
            "description": "<p>Bad Request 若用户的请求中包含错误</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "401",
            "description": "<p>Unauthorized 若用户未登录</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "404",
            "description": "<p>Not Found 若商品未找到</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 Bad Request\n{\n  \"message\": \"商品已经售完\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "wxshop-main/src/main/java/com/hahaen/wxshop/controller/OrderController.java",
    "groupTitle": "订单"
  },
  {
    "type": "DELETE",
    "url": "/order/:id",
    "title": "删除订单",
    "name": "DeleteOrder",
    "group": "订单",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Accept",
            "description": "<p>application/json</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Order",
            "optional": false,
            "field": "data",
            "description": "<p>刚刚删除的订单</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 204 No Content\n{\n  \"data\": {\n      \"id\": 12345,\n      \"expressCompany\": null,\n      \"expressId\": null,\n      \"status\": \"pending\",\n      \"address\": \"XXX\",\n      \"shop\": {\n         \"id\": 12345,\n         \"name\": \"我的店铺\",\n         \"description\": \"我的苹果专卖店\",\n         \"imgUrl\": \"https://img.url\",\n         \"ownerUserId\": 12345,\n         \"createdAt\": \"2022-03-22T13:22:03Z\",\n         \"updatedAt\": \"2022-03-22T13:22:03Z\"\n       },\n       \"goods\": [\n         {\n             \"id\": 12345,\n             \"name\": \"肥皂\",\n             \"description\": \"纯天然无污染肥皂\",\n             \"details\": \"这是一块好肥皂\",\n             \"imgUrl\": \"https://img.url\",\n             \"address\": \"XXX\",\n             \"price\": 500,\n             \"number\": 10,\n             \"createdAt\": \"2022-03-22T13:22:03Z\",\n             \"updatedAt\": \"2022-03-22T13:22:03Z\"\n         },\n         {\n               ...\n         }\n      ]\n    }\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "400",
            "description": "<p>Bad Request 若用户的请求中包含错误</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "401",
            "description": "<p>Unauthorized 若用户未登录</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "403",
            "description": "<p>Forbidden 若用户删除非自己订单</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "404",
            "description": "<p>Not Found 若订单未找到</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 Not Found\n{\n  \"message\": \"Not Found\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "wxshop-main/src/main/java/com/hahaen/wxshop/controller/OrderController.java",
    "groupTitle": "订单"
  },
  {
    "type": "get",
    "url": "/order",
    "title": "获取当前用户名下的所有订单",
    "name": "GetOrder",
    "group": "订单",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Accept",
            "description": "<p>application/json</p>"
          }
        ]
      }
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "pageNum",
            "description": "<p>页数，从1开始</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "pageSize",
            "description": "<p>每页显示的数量</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "allowedValues": [
              "pending/paid/delivered/received"
            ],
            "optional": true,
            "field": "status",
            "description": "<p>订单状态：pending 待付款 paid 已付款 delivered 物流中 received 已收货</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "pageNum",
            "description": "<p>页数，从1开始</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "pageSize",
            "description": "<p>每页显示的数量</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "totalPage",
            "description": "<p>共有多少页</p>"
          },
          {
            "group": "Success 200",
            "type": "Order",
            "optional": false,
            "field": "data",
            "description": "<p>订单列表</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"pageNum\": 1,\n  \"pageSize\": 10,\n  \"totalPage\": 5,\n  \"data\": [\n     {\n      \"id\": 12345,\n      \"expressCompany\": null,\n      \"expressId\": null,\n      \"status\": \"pending\",\n      \"totalPrice\": 10000,\n      \"address\": \"XXX\",\n      \"shop\": {\n         \"id\": 12345,\n         \"name\": \"我的店铺\",\n         \"description\": \"我的苹果专卖店\",\n         \"imgUrl\": \"https://img.url\",\n         \"ownerUserId\": 12345,\n         \"createdAt\": \"2022-03-22T13:22:03Z\",\n         \"updatedAt\": \"2022-03-22T13:22:03Z\"\n       },\n       \"goods\": [\n         {\n             \"id\": 12345,\n             \"name\": \"肥皂\",\n             \"description\": \"纯天然无污染肥皂\",\n             \"details\": \"这是一块好肥皂\",\n             \"imgUrl\": \"https://img.url\",\n             \"address\": \"XXX\",\n             \"price\": 500,\n             \"number\": 10,\n             \"createdAt\": \"2022-03-22T13:22:03Z\",\n             \"updatedAt\": \"2022-03-22T13:22:03Z\"\n         },\n         {\n               ...\n         }\n      ]\n    },\n    {\n         ...\n    }\n  ]\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "401",
            "description": "<p>Unauthorized 若用户未登录</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 401 Unauthorized\n{\n  \"message\": \"Unauthorized\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "wxshop-main/src/main/java/com/hahaen/wxshop/controller/OrderController.java",
    "groupTitle": "订单"
  },
  {
    "type": "PATCH",
    "url": "/order/:id",
    "title": "更新订单(只能更新物流信息/签收状态)",
    "name": "UpdateOrder",
    "group": "订单",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Accept",
            "description": "<p>application/json</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Content-Type",
            "description": "<p>application/json</p>"
          }
        ]
      }
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "id",
            "description": "<p>订单ID</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n    \"id\": 12345,\n    \"expressCompany\": \"圆通\",\n    \"expressId\": \"YTO1234\",\n}\n{\n    \"id\": 12345,\n    \"status\": \"RECEIVED\"\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Order",
            "optional": false,
            "field": "data",
            "description": "<p>更新后的订单</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"data\": {\n      \"id\": 12345,\n      \"expressCompany\": null,\n      \"expressId\": null,\n      \"status\": \"pending\",\n      \"address\": \"XXX\",\n      \"shop\": {\n         \"id\": 12345,\n         \"name\": \"我的店铺\",\n         \"description\": \"我的苹果专卖店\",\n         \"imgUrl\": \"https://img.url\",\n         \"ownerUserId\": 12345,\n         \"createdAt\": \"2022-03-22T13:22:03Z\",\n         \"updatedAt\": \"2022-03-22T13:22:03Z\"\n       },\n       \"goods\": [\n         {\n             \"id\": 12345,\n             \"name\": \"肥皂\",\n             \"description\": \"纯天然无污染肥皂\",\n             \"details\": \"这是一块好肥皂\",\n             \"imgUrl\": \"https://img.url\",\n             \"address\": \"XXX\",\n             \"price\": 500,\n             \"number\": 10,\n             \"createdAt\": \"2022-03-22T13:22:03Z\",\n             \"updatedAt\": \"2022-03-22T13:22:03Z\"\n         },\n         {\n               ...\n         }\n      ]\n    }\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "400",
            "description": "<p>Bad Request 若用户的请求中包含错误</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "401",
            "description": "<p>Unauthorized 若用户未登录</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "403",
            "description": "<p>Forbidden 若用户修改非自己店铺的订单</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "404",
            "description": "<p>Not Found 若订单未找到</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 Not Found\n{\n  \"message\": \"Not Found\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "wxshop-main/src/main/java/com/hahaen/wxshop/controller/OrderController.java",
    "groupTitle": "订单"
  },
  {
    "type": "post",
    "url": "/shoppingCart",
    "title": "加购物车",
    "name": "AddShoppingCart",
    "group": "购物车",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Accept",
            "description": "<p>application/json</p>"
          },
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Content-Type",
            "description": "<p>application/json</p>"
          }
        ]
      }
    },
    "parameter": {
      "examples": [
        {
          "title": "Request-Example:",
          "content": "{\n  \"goods\": [\n    {\n        \"id\": 12345,\n        \"number\": 10,\n    },\n    {\n        ...\n    }\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "ShoppingCart",
            "optional": false,
            "field": "data",
            "description": "<p>更新后的该店铺物品列表</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"data\": {\n      \"shop\": {\n         \"id\": 12345,\n         \"name\": \"我的店铺\",\n         \"description\": \"我的苹果专卖店\",\n         \"imgUrl\": \"https://img.url\",\n         \"ownerUserId\": 12345,\n         \"createdAt\": \"2022-03-22T13:22:03Z\",\n         \"updatedAt\": \"2022-03-22T13:22:03Z\"\n       },\n       \"goods\": [\n         {\n             \"id\": 12345,\n             \"name\": \"肥皂\",\n             \"description\": \"纯天然无污染肥皂\",\n             \"details\": \"这是一块好肥皂\",\n             \"imgUrl\": \"https://img.url\",\n             \"address\": \"XXX\",\n             \"price\": 500,\n             \"number\": 10,\n             \"createdAt\": \"2022-03-22T13:22:03Z\",\n             \"updatedAt\": \"2022-03-22T13:22:03Z\"\n         },\n         {\n               ...\n         }\n      ]\n    }\n  }\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "400",
            "description": "<p>Bad Request 若用户的请求中包含错误</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "401",
            "description": "<p>Unauthorized 若用户未登录</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "404",
            "description": "<p>Not Found 若商品未找到</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 401 Unauthorized\n{\n  \"message\": \"Unauthorized\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "wxshop-main/src/main/java/com/hahaen/wxshop/controller/ShoppingCartController.java",
    "groupTitle": "购物车"
  },
  {
    "type": "delete",
    "url": "/shoppingCart/:goodsId",
    "title": "删除当前用户购物车中指定的商品",
    "name": "DeleteShoppingCart",
    "group": "购物车",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Accept",
            "description": "<p>application/json</p>"
          }
        ]
      }
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "goodsId",
            "description": "<p>要删除的商品ID</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "ShoppingCart",
            "optional": false,
            "field": "data",
            "description": "<p>更新后的该店铺物品列表</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"data\": {\n      \"shop\": {\n         \"id\": 12345,\n         \"name\": \"我的店铺\",\n         \"description\": \"我的苹果专卖店\",\n         \"imgUrl\": \"https://img.url\",\n         \"ownerUserId\": 12345,\n         \"createdAt\": \"2022-03-22T13:22:03Z\",\n         \"updatedAt\": \"2022-03-22T13:22:03Z\"\n       },\n       \"goods\": [\n         {\n             \"id\": 12345,\n             \"name\": \"肥皂\",\n             \"description\": \"纯天然无污染肥皂\",\n             \"details\": \"这是一块好肥皂\",\n             \"imgUrl\": \"https://img.url\",\n             \"address\": \"XXX\",\n             \"price\": 500,0\n             \"number\": 10,\n             \"createdAt\": \"2022-03-22T13:22:03Z\",\n             \"updatedAt\": \"2022-03-22T13:22:03Z\"\n         },\n         {\n               ...\n         }\n      ]\n    }\n  }\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "401",
            "description": "<p>Unauthorized 若用户未登录</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 401 Unauthorized\n{\n  \"message\": \"Unauthorized\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "wxshop-main/src/main/java/com/hahaen/wxshop/controller/ShoppingCartController.java",
    "groupTitle": "购物车"
  },
  {
    "type": "get",
    "url": "/shoppingCart",
    "title": "获取当前用户名下的所有购物车物品",
    "name": "GetShoppingCart",
    "group": "购物车",
    "header": {
      "fields": {
        "Header": [
          {
            "group": "Header",
            "type": "String",
            "optional": false,
            "field": "Accept",
            "description": "<p>application/json</p>"
          }
        ]
      }
    },
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "pageNum",
            "description": "<p>页数，从1开始</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "pageSize",
            "description": "<p>每页显示的数量</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "pageNum",
            "description": "<p>页数，从1开始</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "pageSize",
            "description": "<p>每页显示的数量</p>"
          },
          {
            "group": "Success 200",
            "type": "Number",
            "optional": false,
            "field": "totalPage",
            "description": "<p>共有多少页</p>"
          },
          {
            "group": "Success 200",
            "type": "ShoppingCart",
            "optional": false,
            "field": "data",
            "description": "<p>购物车物品列表，按照店铺分组</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "HTTP/1.1 200 OK\n{\n  \"pageNum\": 1,\n  \"pageSize\": 10,\n  \"totalPage\": 5,\n  \"data\": [\n    {\n      \"shop\": {\n         \"id\": 12345,\n         \"name\": \"我的店铺\",\n         \"description\": \"我的苹果专卖店\",\n         \"imgUrl\": \"https://img.url\",\n         \"ownerUserId\": 12345,\n         \"createdAt\": \"2022-03-22T13:22:03Z\",\n         \"updatedAt\": \"2022-03-22T13:22:03Z\"\n       },\n       \"goods\": [\n         {\n             \"id\": 12345,\n             \"name\": \"肥皂\",\n             \"description\": \"纯天然无污染肥皂\",\n             \"details\": \"这是一块好肥皂\",\n             \"imgUrl\": \"https://img.url\",\n             \"price\": 500,\n             \"number\": 10,\n             \"createdAt\": \"2022-03-22T13:22:03Z\",\n             \"updatedAt\": \"2022-03-22T13:22:03Z\"\n         },\n         {\n               ...\n         }\n      ]\n    }\n  ]\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "401",
            "description": "<p>Unauthorized 若用户未登录</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 401 Unauthorized\n{\n  \"message\": \"Unauthorized\"\n}",
          "type": "json"
        }
      ]
    },
    "version": "0.0.0",
    "filename": "wxshop-main/src/main/java/com/hahaen/wxshop/controller/ShoppingCartController.java",
    "groupTitle": "购物车"
  }
] });
