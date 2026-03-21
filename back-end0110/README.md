# 后端0110

--------------------------

## 结构

└─back-end0110
        app.py
        app_module.py
        README.md

----------------------------------

## 题目

- 就是利用Python搭建一个和0111、0101差不多的简单Web架子

- 我把app.py作为程序的主体。专门用app_module.py存储待办事项列表的逻辑,列表todos本体也在里面

  - app_module如下(只给了部分)

    ```
    todos=[]
    
    def add_todos(todos,work_input):
        work = work_input.strip()
        if work:
            todo = {
                "title": work,
                "done": False,
            }
            todos.append(todo)
            return f"已添加事项:{work}"
        else:
            return "待办事项不能为空"
            
    ........

- 接下来便是实现步骤

  - 导入Flask框架

    ```
    from flask import Flask,request
    import app_module
    from app_module import add_todos,delete_todos
    
    app = Flask(__name__)
    
    .....
    
    if __name__ == '__main__':
        app.run(debug=True)

  - 创建**/todos**路由

    - 处理*get*请求

      ```
      @app.route('/todos', methods=['GET'])
      def return_todos():
          return {"todos": app_module.todos}

    - 处理*post*请求

      ```
      @app.route('/todos', methods=['POST'])
      def insert_todos():
          data = request.get_json()
          if data is None:
              return {"error": "请发送 JSON 数据"}, 400
          work_input = data.get("work", "")
          result = add_todos(app_module.todos, work_input)
          return {"message": result}, 201

  - 创建**/todos/int:index**路由

    - 处理*get*请求

      ```
      @app.route('/todos/<int:index>', methods=['GET'])
      def get_todo_by_index(index):
          if 0 <= index < len(app_module.todos):
              return {"todo": app_module.todos[index]}
          else:
              return {"error": "待办事项不存在"}, 404

    - 处理*delete*请求

      ```
      @app.route('/todos/<int:index>', methods=['DELETE'])
      def delete_todo_by_index(index):
          result = delete_todos(app_module.todos,index)
          return {"message": result}, 201

- 测试部分

  - /todos

    - 获取列表

      <div style="margin: 10px 0;">
                  <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/0110/0110get1.png" style="width:75%;" />
                </div>

    - 添加事项

      <div style="margin: 10px 0;">
                  <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/0110/0110post.png" style="width:75%;" />
                </div>

  - /todos/int:index

    - 返回对应事项

      <div style="margin: 10px 0;">
                  <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/0110/0110get2.png" style="width:75%;" />
                </div>

    - 删除对应事项

      <div style="margin: 10px 0;">
                  <img src="https://pub-f74a867aaa984b688fc9aac4f0ff1fbd.r2.dev/0110/0110delete.png" style="width:75%;" />
                </div>

- 跟0101的结构还是比较相似，但框架确实有不同处。然后在处理的时候，把数据处理为json格式还是有点麻烦。这个过程中学习了Python基础，Flask框架等等，收获很多